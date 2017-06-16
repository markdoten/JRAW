package net.dean.jraw.test

import net.dean.jraw.models.VoteDirection
import net.dean.jraw.references.SubmissionReference
import net.dean.jraw.test.util.TestConfig
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe

class SubmissionReferenceTest : Spek({
    val ref: SubmissionReference = TestConfig.reddit.submission("65eeke")

    xdescribe("upvote/downvote/unvote") {
        it("should have an effect on a model") {
            fun expectVote(dir: VoteDirection) {
                val value = if (dir == VoteDirection.UP) true else if (dir == VoteDirection.DOWN) false else null
                // TODO
//                ref.comments().submission.likes.should.equal(value)
            }
            ref.upvote()
            expectVote(VoteDirection.UP)

            ref.downvote()
            expectVote(VoteDirection.DOWN)

            ref.unvote()
            expectVote(VoteDirection.NONE)
        }
    }
})
