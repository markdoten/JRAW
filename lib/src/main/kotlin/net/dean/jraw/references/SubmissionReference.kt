package net.dean.jraw.references

import net.dean.jraw.RedditClient
import net.dean.jraw.models.KindConstants

/**
 * A Reference to a link or text submitted to a subreddit, like [this one](https://www.reddit.com/comments/6afe8u).
 */
class SubmissionReference internal constructor(reddit: RedditClient, id: String) :
    PublicContributionReference(reddit, id, KindConstants.SUBMISSION) {

//    @EndpointImplementation(arrayOf(Endpoint.GET_COMMENTS_ARTICLE))
//    fun comments(): RootCommentNode = RootCommentNode(reddit.request { it.path("/comments/$subject") }.json)
}
