package xpc.sonu.httpServerTest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var server: ServerApp? = null
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            when {
                !running -> startServer()
                else -> stopServer()
            }
        }
    }
    private fun startServer(){
        try {
            server = ServerApp(this, Integer.parseInt(editPORT.text.toString()))
            server!!.start()
            logIt("Server started @http://${server?.hostname}:${server?.listeningPort}")
            text_server_status.text = "Server is running @ ${server?.hostname.toString()}:${server?.listeningPort}"
            running = true
            button.text = "STOP"
        }catch (e : Exception){
            logIt(e.toString())
        }
    }
    private fun stopServer() {
        when {
            server != null -> {
                server?.stop()
                logIt("Server stopped!")
                running = false
                text_server_status.text = "Server is not running!!!"
                button.text = "START"
            }
        }
    }
    fun logIt(string: String){
        Log.e(">>>>>", string)
    }
    override fun onDestroy() {
        super.onDestroy()
        stopServer()
    }
}