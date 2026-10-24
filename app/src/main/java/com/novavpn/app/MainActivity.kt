package com.novavpn.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

private lateinit var statusText: TextView
private lateinit var connectButton: Button
private lateinit var serverText: TextView

private var isConnecting = false

private val backgroundColor = Color.rgb(8, 15, 32)
private val blueColor = Color.rgb(37, 99, 235)
private val textColor = Color.WHITE
private val mutedColor = Color.rgb(160, 177, 205)

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    window.statusBarColor = backgroundColor
    window.navigationBarColor = backgroundColor
    window.decorView.systemUiVisibility = 0

    val root = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        setPadding(dp(24), dp(35), dp(24), dp(24))
        setBackgroundColor(backgroundColor)
    }

    val title = TextView(this).apply {
        text = "🛡️ NOVA VPN"
        textSize = 28f
        setTextColor(textColor)
        typeface = Typeface.DEFAULT_BOLD
        gravity = Gravity.CENTER
    }

    val subtitle = TextView(this).apply {
        text = "YOUR PRIVACY, YOUR CONTROL"
        textSize = 12f
        setTextColor(mutedColor)
        gravity = Gravity.CENTER
    }

    statusText = TextView(this).apply {
        text = "Not connected"
        textSize = 23f
        setTextColor(textColor)
        typeface = Typeface.DEFAULT_BOLD
        gravity = Gravity.CENTER
    }

    val description = TextView(this).apply {
        text = "Your connection is not protected by Nova VPN."
        textSize = 14f
        setTextColor(mutedColor)
        gravity = Gravity.CENTER
    }

    connectButton = Button(this).apply {
        text = "Connect"
        textSize = 18f
        setTextColor(textColor)
        setBackgroundColor(blueColor)
        isAllCaps = false
    }

    val serverLabel = TextView(this).apply {
        text = "VPN SERVER"
        textSize = 12f
        setTextColor(mutedColor)
    }

    serverText = TextView(this).apply {
        text = "Automatic server selection"
        textSize = 17f
        setTextColor(textColor)
        setPadding(dp(16), dp(16), dp(16), dp(16))
        setBackgroundColor(Color.rgb(17, 29, 50))
    }

    val footer = TextView(this).apply {
        text = "WireGuard support planned\nSecure VPN connectivity requires a configured provider."
        textSize = 12f
        setTextColor(mutedColor)
        gravity = Gravity.CENTER
    }

    root.addView(title, matchWrap())
    root.addView(subtitle, matchWrap())
    root.addView(
        statusText,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = dp(65)
        }
    )
    root.addView(description, matchWrap())
    root.addView(
        connectButton,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dp(58)
        ).apply {
            topMargin = dp(30)
            bottomMargin = dp(45)
        }
    )
    root.addView(serverLabel, matchWrap())
    root.addView(
        serverText,
        LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = dp(10)
        }
    )
    root.addView(footer, matchWrap().apply {
        topMargin = dp(35)
    })

    connectButton.setOnClickListener {
        if (!isConnecting) {
            statusText.text = "VPN not configured"
            description.text =
                "Connect a compatible VPN provider to enable secure connections."
            connectButton.text = "Not available yet"
            serverText.text = "No VPN server configured"
            isConnecting = true
        } else {
            statusText.text = "Not connected"
            description.text =
                "Your connection is not protected by Nova VPN."
            connectButton.text = "Connect"
            serverText.text = "Automatic server selection"
            isConnecting = false
        }
    }

    setContentView(root)
}

private fun dp(value: Int): Int =
    (value * resources.displayMetrics.density).toInt()

private fun matchWrap() =
    LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

}
