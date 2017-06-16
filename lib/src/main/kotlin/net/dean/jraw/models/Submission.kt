package net.dean.jraw.models

import com.squareup.moshi.Json
import net.dean.jraw.databind.RedditModel
import java.util.*

@RedditModel(kind = "t3")
data class Submission(
    /** Username of the author (e.g. "Unidan") */
    @Json(name = "author") val authorName: String,

    /** Flair text to be displayed next to the author's name, if any */
    @Json(name = "author_flair_text")
    val authorFlair: String?,

    /** Submissions are archived once they reach a certain age. At that point, they become unmodifiable */
    val archived: Boolean,

    @Json(name = "can_gild")
    override val canGild: Boolean,

    @Json(name = "created_utc")
    override val created: Date,

    @Json(name = "contest_mode")
    val contestMode: Boolean,

    /** The status of the person who created this Submission. Always non-null */
    override val distinguished: DistinguishedStatus,

    /**
     * Domain of this Submission's URL. If this is a self post, this property will be equal to `self.{subreddit}`,
     * otherwise it will be the actual link domain (e.g. "i.imgur.com")
     *
     * @see [isSelfPost]
     */
    val domain: String,

    /**
     * The last time this Submission was edited, or null if it hasn't been
     */
    val edited: Date?,

    /**
     * The full name of the submission, equivalent to `"t3_" + id`
     */
    @Json(name = "name")
    val fullName: String,

    override val gilded: Short,

    /** Is this post hidden from the current user? */
    val hidden: Boolean,

    /** If reddit is masking this Submission's score */
    @Json(name = "hide_score")
    val hideScore: Boolean,

    /** The unique ID given to this Submission by reddit */
    val id: String,

    /** If this Submission is a self (text-only) post */
    @Json(name = "is_self")
    val isSelfPost: Boolean,

    /** Logged-in-user vote status. True for upvote, false for downvote, null for no vote. */
    override val likes: Boolean?,

    /** Flair to display next to the Submission, if any */
    @Json(name = "link_flair_text")
    val linkFlairText: String?,

    /** If the moderators/admins have prevented creating new comments on this submission */
    val locked: Boolean,

    /** If this Submission contains adult content */
    @Json(name = "over_18")
    val nsfw: Boolean,

    /** URL relative to reddit.com to access this Submission from a web browser */
    val permalink: String,

    /** The type of content reddit thinks this submission links to */
    @Json(name = "post_hint")
    val postHint: String?,

    /** If the subreddit this Submission has been posted to has been quarantined */
    val quarantine: Boolean,

    /** The number of reports this Submission has received */
    @Json(name = "num_reports")
    val reports: Int,

    /** If the user has saved this to their profile for later */
    val saved: Boolean,

    override val score: Int,

    /** Markdown-formatted content, non-null when [isSelfPost] is true */
    val selftext: String?,

    /** If reddit thinks this Submission is spam */
    val spam: Boolean,

    /** If the creator of this Submission has marked it as containing spoilers */
    val spoiler: Boolean,

    /** Stickied submissions appear as the first submissions when browsing a subreddit by 'hot' */
    val stickied: Boolean,

    /** Name of the subreddit this Submission is hosted in */
    val subreddit: String,

    /**
     * Full name of [subreddit]
     *
     * @see Subreddit.fullName
     */
    @Json(name = "subreddit_id")
    val subredditFullName: String,

    /** The suggested default comment sort */
    @Json(name = "suggested_sort")
    val suggestedSort: CommentSort?,

    /** An empty string for self posts, otherwise a reddit-generated-and-hosted thumbnail */
    val thumbnail: String,

    /** Title of the submission */
    val title: String,

    /** An absolute URL to the comments for a self post, otherwise an absolute URL to the Submission content */
    val url: String,

    /**
     * If the user has visited this Submission before. Requires a call to [net.dean.jraw.Endpoint.POST_STORE_VISITS] and
     * a subscription to reddit Gold
     */
    val visited: Boolean

    // TODO:
//    val userReports: List<UserReport>,
//    val modReports: List<ModReport>,
//    val media: ?
//    val preview: Preview,
//    val vote: VoteDirection // instead of `likes`
//    val postHint: Hint
) : RedditObject(KindConstants.SUBMISSION), Created, Distinguishable, Gildable, Votable
