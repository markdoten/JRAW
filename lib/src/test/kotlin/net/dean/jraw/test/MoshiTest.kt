package net.dean.jraw.test

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.winterbe.expekt.should
import net.dean.jraw.databind.Enveloped
import net.dean.jraw.databind.EnvelopedListAdapterFactory
import net.dean.jraw.databind.RedditModel
import net.dean.jraw.databind.RedditModelJsonAdapter
import net.dean.jraw.models.RedditObject
import org.intellij.lang.annotations.Language
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xit
import kotlin.properties.Delegates

// Final class with no generic types
@RedditModel(kind = "test")
data class ConcreteTestModel(val foo: String, val bar: Int) : RedditObject("test")

// Listing that can only have one kind of child
@RedditModel(kind = "Listing")
data class ConcreteTestListing(val before: String?, val after: String?, @Enveloped val children: List<ConcreteTestModel>)

// Listing that can have generic children
@RedditModel(kind = "Listing")
data class GenericTestListing<out T>(val before: String?, val after: String?, @Enveloped val children: List<T>)

class MoshiTest : Spek({
    var moshi: Moshi by Delegates.notNull()
    beforeEachTest {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(RedditModelJsonAdapter.Factory())
            .add(EnvelopedListAdapterFactory())
            .build()
    }

    @Language("JSON")
    val listingJson = """
{
    "kind": "Listing",
    "data": {
        "children": [
            {
                "kind": "test",
                "data": { "foo": "abc", "bar": 123 }
            }
        ],
        "after": null,
        "before": null
    }
}
"""

    it("should deserialize using @Enveloped List as root") {
        @Language("JSON")
        val json = """
[
    {
        "kind": "test",
        "data": { "foo": "abc", "bar": 123 }
    },
    {
        "kind": "test",
        "data": { "foo": "def", "bar": 456 }
    }
]
"""
        val type = Types.newParameterizedType(List::class.java, ConcreteTestModel::class.java)
        val adapter = moshi.adapter<List<ConcreteTestModel>>(type, Enveloped::class.java)

        val model = adapter.fromJson(json)
        model.should.not.be.`null`
        model.should.have.size(2)
        model!![0].foo.should.equal("abc")
        model[1].foo.should.equal("def")
    }

    it("should deserialize using a concrete Listing as root") {

        val type = Types.newParameterizedType(ConcreteTestListing::class.java, ConcreteTestModel::class.java)
        val adapter = moshi.adapter<ConcreteTestListing>(type, Enveloped::class.java)
        val listing = adapter.fromJson(listingJson)!!

        listing.before.should.be.`null`
        listing.after.should.be.`null`
        listing.children.should.have.size(1)
        listing.children[0].foo.should.equal("abc")
        listing.children[0].bar.should.equal(123)
    }

    xit("should deserialize using a generic Listing as root") {
        val type = Types.newParameterizedType(GenericTestListing::class.java, ConcreteTestModel::class.java)

//         This fails: "No JsonAdapter for T annotated [@net.dean.jraw.databind.Enveloped()]"

        val adapter = moshi.adapter<GenericTestListing<ConcreteTestModel>>(type)
        val listing = adapter.fromJson(listingJson)!!

        listing.before.should.be.`null`
        listing.after.should.be.`null`
        listing.children.should.have.size(1)
        listing.children[0].foo.should.equal("abc")
        listing.children[0].bar.should.equal(123)
    }
})
