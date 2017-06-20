package net.dean.jraw.references

import com.fasterxml.jackson.module.kotlin.treeToValue
import net.dean.jraw.*
import net.dean.jraw.JrawUtils.jackson
import net.dean.jraw.models.Comment
import net.dean.jraw.models.ThingType
import net.dean.jraw.models.VoteDirection

/**
 * Base class for References that can be publicly voted upon and saved (in essence, Submissions and Comments).
 *
 * Provides methods for [upvoting][upvote], [downvoting][downvote], and [removing the current vote][unvote], and also
 * one method for [manually setting the vote direction by enum value][setVote].
 */
abstract class PublicContributionReference internal constructor(reddit: RedditClient, id: String, val type: ThingType) :
    AbstractReference<String>(reddit, id) {

    /** Equivalent to `setVote(VoteDirection.UP)` */
    fun upvote() { setVote(VoteDirection.UP) }

    /** Equivalent to `setVote(VoteDirection.DOWN)` */
    fun downvote() { setVote(VoteDirection.DOWN) }

    /** Equivalent to `setVote(VoteDirection.NONE)` */
    fun unvote() { setVote(VoteDirection.NONE) }

    /**
     * Votes on a model on behalf of the user.
     *
     * From the reddit API docs:
     *
     * > Note: votes must be cast by humans. That is, API clients proxying a human's action one-for-one are OK, but bots
     *   deciding how to vote on content or amplifying a human's vote are not.
     */
    @EndpointImplementation(arrayOf(Endpoint.POST_VOTE))
    fun setVote(dir: VoteDirection) {
        val value = when (dir) {
            VoteDirection.UP -> 1
            VoteDirection.NONE -> 0
            VoteDirection.DOWN -> -1
        }
        reddit.request {
            it.endpoint(Endpoint.POST_VOTE).post(mapOf(
                "dir" to value.toString(),
                "id" to type.prefix + '_' + subject
            ))
        }
    }

    /**
     * Creates a comment in response to this submission of comment
     *
     * @throws ApiException Most commonly for ratelimiting.
     */
    @Throws(ApiException::class)
    @EndpointImplementation(arrayOf(Endpoint.POST_COMMENT))
    fun reply(text: String): Comment {
        val json = reddit.request {
            it.endpoint(Endpoint.POST_COMMENT)
                .post(mapOf(
                    "api_type" to "json",
                    "text" to text,
                    "thing_id" to "${ThingType.SUBMISSION.prefix}_$subject"
                ))
        }.json

        // Check for errors
        JrawUtils.handleApiErrors(json)

        // Deserialize specific JSON node to a Comment
        val commentNode = json.get("data")?.get("things")?.get(0) ?:
            throw IllegalArgumentException("Unexpected JSON structure")
        return jackson.treeToValue(commentNode)
    }
}