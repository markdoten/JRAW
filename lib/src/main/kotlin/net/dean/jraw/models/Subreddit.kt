package net.dean.jraw.models

import com.squareup.moshi.Json
import net.dean.jraw.databind.RedditModel
import java.util.*

@RedditModel(kind = KindConstants.SUBREDDIT)
data class Subreddit(
    /**
     * How many accounts are active on this subreddit at one time. If [accountsActiveIsFuzzed], this will not be the
     * exact number of accounts.
     */
    @Json(name = "accounts_active")
    val accountsActive: Int,

    /** If true, [accountsActive] will be inexact */
    @Json(name = "accounts_active_is_fuzzed")
    val accountsActiveIsFuzzed: Boolean,

    /** How many minutes reddit hides new comments for */
    @Json(name = "comment_score_hide_mins")
    val commentScoreHideMins: Int,

    @Json(name = "created_utc")
    override val created: Date,

    /** The result of "t5_" + [id] */
    @Json(name = "name")
    val fullName: String,

    /** A unique base-36 identifier for this Subreddit, e.g. "2qh0u" in the case of /r/pics */
    val id: String,

    /** A hex color used primarily to style the header of the mobile site */
    @Json(name = "key_color")
    val keyColor: String,

    /** Language code, e.g. "en" for English */
    val lang: String,

    /** Name without the "/r/" prefix: "pics", "funny", etc. */
    @Json(name = "display_name")
    val name: String,

    /** If this subreddit contains mostly adult content */
    val over18: Boolean,

    /**
     * If this subreddit has been quarantined. See
     * [here](https://reddit.zendesk.com/hc/en-us/articles/205701245-Quarantined-Subreddits) for more.
     */
    val quarantine: Boolean,

    /** Sidebar content in raw Markdown */
    @Json(name = "description")
    val sidebar: String,

    /** Whether the subreddit supports Markdown spoilers */
    @Json(name = "spoilers_enabled")
    val spoilersEnabled: Boolean,

    /** What type of submissions can be submitted to this subreddit */
    @Json(name = "submission_type")
    val submissionType: SubmissionType,

    /** The text on the button that users click to submit a link */
    @Json(name = "submit_link_label")
    val submitLinkLabel: String?,

    /** The text on the button that users click to submit a self post */
    val submitTextLabel: String?,
    @Json(name = "subreddit_type") val subredditAvailability: Type,

    /** The amount of subscribers this subreddit has */
    val subscribers: Int,

    /** The suggested default comment sort */
    @Json(name = "suggested_comment_sort")
    val suggestedCommentSort: CommentSort?,

    /** The URL to access this subreddit relative to reddit.com. For example, "/r/pics" */
    val url: String,

    @Json(name = "user_is_muted") val isUserMuted: Boolean?,
    @Json(name = "user_is_banned") val isUserBanned: Boolean?,
    @Json(name = "user_is_contributor") val isUserContributor: Boolean?,
    @Json(name = "user_is_moderator") val isUserModerator: Boolean?,
    @Json(name = "user_is_subscriber") val isUserIsSubscriber: Boolean?,

    /** If this subreddit's wiki is enabled. A value of null means that the wiki is not enabled. */
    @Json(name = "wiki_enabled")
    val wikiEnabled: Boolean?
) : RedditObject(KindConstants.SUBREDDIT), Created {

    enum class Type {
        /** Open to all users */
        @Json(name = "public") PUBLIC,
        /** Only approved members can view and submit */
        @Json(name = "private") PRIVATE,
        /** Anyone can view, but only some are approved to submit links */
        @Json(name = "restricted") RESTRICTED,
        /** Only users with reddit gold can post */
        @Json(name = "gold_restricted") GOLD_RESTRICTED,
        @Json(name = "archived") ARCHIVED
    }

    /** An enumeration of how a subreddit can restrict the type of submissions that can be posted  */
    enum class SubmissionType {
        /** Links and self posts  */
        @Json(name = "any") ANY,
        /** Only links  */
        @Json(name = "link") LINK,
        /** Only self posts  */
        @Json(name = "self") SELF,
        /** Restricted subreddit  */
        @Json(name = "none") NONE
    }
}

