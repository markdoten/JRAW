package net.dean.jraw.models

import com.squareup.moshi.Json
import net.dean.jraw.databind.RedditModel
import java.util.*

/**
 * This class represents a comment on a [Submission].
 *
 * Comments are usually created through [CommentNode]s:
 *
 * ```kotlin
 * val firstTopLevelComment: Comment = reddit.submission(id).comments().replies[0].comment
 * ```
 */
@RedditModel(kind = KindConstants.COMMENT)
data class Comment(
    /** If this comment belongs to a Submission which has been marked as unmodifiable */
    val archived: Boolean,

    /** Username of the user that created this comment */
    val author: String,

    /** Flair to appear next to the creator of this comment's name, if any */
    val authorFlairText: String?,

    /** The Markdown-formatted body of this comment */
    val body: String,

    /** If the currently logged-in-user can give reddit Gold to this comment */
    val canGild: Boolean,

    /**
     * Get this comments controversiality level. A comment is considered controversial if it has a large number of both
     * upvotes and downvotes. 0 means not controversial, 1 means controversial.
     */
    val controversiality: Int,

    /** When this comment was created */
    @Json(name = "created_utc")
    override val created: Date,

    override val distinguished: DistinguishedStatus?,

    /** When this comment was edited, if any */
    val edited: Date?,

    /** The full name of the comment (`t1_` + [id]) */
    @Json(name = "name")
    val fullName: String,

    /** How many times this comment was given reddit Gold */
    val gilded: Short,

    /** The unique base 36 identifier given to this comment by reddit */
    val id: String,

    override val likes: Boolean?,

    /** If the user has saved this comment or not */
    val saved: Boolean,

    override val score: Int,

    /** If reddit is masking the score of a new comment */
    @Json(name = "score_hidden")
    val scoreHidden: Boolean,

    /** If this comment is shown at the top of the comment section, regardless of score */
    val stickied: Boolean,

    /** The full name of the submission that this comment is contained in (`t3_XXXXX`) */
    @Json(name = "link_id")
    val submissionFullName: String,

    /** The name of the subreddit (e.g. "pics") */
    @Json(name = "subreddit")
    val subredditName: String,

    /** The subreddit's full name */
    @Json(name = "subreddit_id")
    val subredditFullName: String,

    /** The restrictions for accessing this subreddit */
    @Json(name = "subreddit_type")
    val subredditType: Subreddit.Type
) : RedditObject(KindConstants.COMMENT), Created, Distinguishable, Votable
