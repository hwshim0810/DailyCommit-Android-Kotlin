package xyz.laziness.dailycommit.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.utils.datetime.DateUtil
import java.util.*


class GitHubGraphView
@JvmOverloads constructor(context: Context,
                          attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0,
                          defStyleRes: Int = 0) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var viewBitmap: Bitmap? = null

    private var backGroundColor = Color.TRANSPARENT
    private var textColor = Color.BLACK
    private val baseEmptyBlockColor = "#EBEDF0"

    private val monthTextPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val blockPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
        }
    }
    @get:JvmName("getMatrix_") private val matrix = Matrix()
    private val paint = Paint()
    private val point = Point()

    private var showMonth = true
    private var viewHeight = 0
    private val verticalBlockNum = 7
    private val marginBlock = (1.0f - 0.15f)
    private val daysOfYear = 7*53

    init {
        initView(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun initView(context: Context,
                         attrs: AttributeSet? = null,
                         defStyleAttr: Int = 0,
                         defStyleRes: Int = 0) {
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                .defaultDisplay.getSize(point)
        initAttrs(context.theme.obtainStyledAttributes(attrs,
                R.styleable.GitHubGraphView, defStyleAttr, defStyleRes))
    }

    private fun initAttrs(attrs: TypedArray) {
        showMonth = attrs.getBoolean(R.styleable.GitHubGraphView_showMonth, showMonth)

        attrs.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        viewBitmap?.run {
            canvas?.drawBitmap(this, matrix, paint)
        } ?: onDrawEmptyView(canvas)
    }

    fun onDisplay() {
        if (viewHeight != layoutParams.height)
            layoutParams = layoutParams.apply { height = viewHeight }
        invalidate()
    }

    private fun onDrawEmptyView(canvas: Canvas?) {
        if (!isInEditMode || canvas == null) return

        val rect = Rect()
        canvas.getClipBounds(rect)

        val width = rect.width()
        val viewDimen = ViewDimens(width, daysOfYear)
        val blockWidth = viewDimen.blockWidth
        val spaceWidth = viewDimen.spaceWidth
        val topMargin = viewDimen.topMargin
        val monthTextHeight = viewDimen.monthTextHeight
        val height = viewDimen.height

        blockPaint.color = backGroundColor
        canvas.drawRect(0f, (topMargin + monthTextHeight), width.toFloat(),
                (height + monthTextHeight), blockPaint)

        monthTextPaint.textSize = monthTextHeight
        blockPaint.color = Color.parseColor(baseEmptyBlockColor)

        var x = 0f
        var y = topMargin + monthTextHeight

        (1..daysOfYear).forEach {
            canvas.drawRect(x, y, (x + blockWidth), (y + blockWidth), blockPaint)

            if (it % 7 == 0) {
                x += (blockWidth + spaceWidth)
                y = (topMargin + monthTextHeight)
            } else {
                y += (blockWidth + spaceWidth)
            }
        }

        layoutParams = layoutParams.apply { this.height = height }
    }

    fun onDrawCanvas(contributions: List<ContributionDay>): Bitmap? {
        val listSize = contributions.size

        if (viewBitmap == null && listSize > 0) {
            val width = point.x - resources.getDimensionPixelSize(R.dimen.space_m)
            val viewDimen = ViewDimens(width, listSize)
            val blockWidth = viewDimen.blockWidth
            val spaceWidth = viewDimen.spaceWidth
            val topMargin = viewDimen.topMargin
            val monthTextHeight = viewDimen.monthTextHeight
            val height = viewDimen.height.apply { viewHeight = this }

            viewBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(viewBitmap)

            blockPaint.color = backGroundColor
            canvas.drawRect(0f, (topMargin+monthTextHeight),
                    width.toFloat(), (height+monthTextHeight), blockPaint)

            monthTextPaint.run {
                color = textColor
                textSize = monthTextHeight
            }

            val weekDay = DateUtil.getWeekDay(contributions.first().date)
            var x = 0f
            var y = (weekDay-7) % 7 * (blockWidth + spaceWidth) + (topMargin + monthTextHeight)

            contributions.forEach {
                val date = it.date.apply { add(Calendar.DAY_OF_MONTH, 1) }
                blockPaint.color = Color.parseColor(it.color)
                canvas.drawRect(x, y, (x + blockWidth), (y + blockWidth), blockPaint)

                if (DateUtil.isFirstDayOfWeek(date)) {
                    x += (blockWidth + spaceWidth)
                    y = (topMargin + monthTextHeight)

                    if (DateUtil.isFirstDayOfWeekInMonth(date))
                        canvas.drawText(DateUtil.getShortMonthName(date),
                                x, monthTextHeight, monthTextPaint)

                } else {
                    y += (blockWidth + spaceWidth)
                }
            }

        }

        return viewBitmap
    }

    fun setShowMonth(showMonth: Boolean) {
        this.showMonth = showMonth
        invalidate()
    }

    inner class ViewDimens(val width: Int, val size: Int) {
        private val horizontalBlockNum = getHorizontalBlockNum(size)

        val blockWidth = width / horizontalBlockNum * marginBlock
        val spaceWidth = width / horizontalBlockNum - blockWidth
        val topMargin = if (showMonth) 7f else 0f
        val monthTextHeight = if (showMonth) blockWidth * 1.5f else 0f
        val height = ((blockWidth + spaceWidth)*7 + topMargin + monthTextHeight).toInt()

        private fun getHorizontalBlockNum(size: Int): Int {
            val res = size / verticalBlockNum
            return if (size % verticalBlockNum == 0) res else res+1
        }
    }

}