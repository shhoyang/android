package com.hao.library.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.hao.library.HaoLibrary
import com.hao.library.R
import com.hao.library.extensions.gone
import com.hao.library.extensions.visibility
import com.hao.library.extensions.visible
import com.hao.library.utils.DrawableUtils

/**
 * @author Yang Shihao
 */
class ToolbarLayout : FrameLayout {

    private var toolbarBackgroundColor = 0
    private var showBack = true
    private var backIcon = 0
    private var backIconColor = 0
    private var titleText: String? = null
    private var titleTextSize = 0F
    private var titleTextColor = 0
    private var showLine = true
    private var lineColor = 0

    private var toolbarBack: ImageView? = null
    private var toolbarTitle: TextView? = null
    private var toolbarLine: View? = null

    constructor(context: Context) : this(context, null, 0, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr, 0
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

        var layoutId: Int

        context.obtainStyledAttributes(
            attrs,
            R.styleable.ToolbarLayout,
            defStyleAttr,
            HaoLibrary.CONFIG.toolbarLayoutTheme
        )
            .apply {
                layoutId = getResourceId(R.styleable.ToolbarLayout_toolbarLayoutId, 0)

                toolbarBackgroundColor =
                    getColor(R.styleable.ToolbarLayout_toolbarBackgroundColor, 0)
                showBack = getBoolean(R.styleable.ToolbarLayout_toolbarShowBack, showBack)
                backIcon =
                    getResourceId(R.styleable.ToolbarLayout_toolbarBackIcon, R.drawable.ic_back)
                backIconColor = getColor(
                    R.styleable.ToolbarLayout_toolbarBackIconColor,
                    ContextCompat.getColor(context, R.color.toolbarIconColor)
                )
                titleText = getString(R.styleable.ToolbarLayout_toolbarTitleText)
                titleTextSize = getDimension(
                    R.styleable.ToolbarLayout_toolbarTitleTextSize,
                    resources.getDimension(R.dimen.toolbarTitleTextSize)
                )
                titleTextColor = getColor(
                    R.styleable.ToolbarLayout_toolbarTitleTextColor,
                    ContextCompat.getColor(context, R.color.toolbarTitleTextColor)
                )
                showLine = getBoolean(R.styleable.ToolbarLayout_toolbarShowLine, showLine)
                lineColor = getColor(
                    R.styleable.ToolbarLayout_toolbarLineColor,
                    ContextCompat.getColor(context, R.color.toolbarLineColor)
                )
                recycle()
            }

        View.inflate(context, layoutId, this)
    }

    fun showBack(show: Boolean) {
        toolbarBack?.visibility(show)
    }

    fun setTitleText(text: String) {
        toolbarTitle?.text = text
    }

    fun setTitleText(@StringRes resId: Int) {
        toolbarTitle?.text = context.getString(resId)
    }

    fun setTitleTextColor(color: Int) {
        toolbarTitle?.setTextColor(color)
    }

    fun setIconColor(color: Int) {
        toolbarBack?.apply { DrawableUtils.tint(this, color) }
    }

    fun showLine(show: Boolean) {
        toolbarLine?.visibility(show)
    }

    fun setLineColor(color: Int) {
        toolbarLine?.setBackgroundColor(color)
    }


    fun setBackClickListener(f: () -> Unit) {
        toolbarBack?.setOnClickListener { f() }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        toolbarBack = findViewById(R.id.toolbarBack)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarLine = findViewById(R.id.toolbarLine)

        if (0 != toolbarBackgroundColor) {
            setBackgroundColor(toolbarBackgroundColor)
        }

        toolbarBack?.apply {
            if (showBack) {
                visible()
                setImageResource(backIcon)
                DrawableUtils.tint(this, backIconColor)
            } else {
                gone()
            }
        }

        toolbarTitle?.apply {
            text = titleText
            setTextColor(titleTextColor)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize)
        }

        toolbarLine?.apply {
            if (showLine) {
                visible()
                setBackgroundColor(lineColor)
            } else {
                gone()
            }
        }
    }
}