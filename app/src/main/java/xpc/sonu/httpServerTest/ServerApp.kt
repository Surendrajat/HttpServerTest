package xpc.sonu.httpServerTest

import fi.iki.elonen.NanoHTTPD

internal class ServerApp(private val mainActivity: MainActivity, port: Int) : NanoHTTPD("127.0.0.1", port) {

    override fun serve(session: NanoHTTPD.IHTTPSession): NanoHTTPD.Response {

        var requestHeaders = ""
        for (header in session.headers) requestHeaders += "$header\n"
        mainActivity.logIt(requestHeaders)

        val msg = "<html><body>\n<p>Hello! Your request is:<br/>$requestHeaders<br/>‍‌‌‌</p>\n</body></html>"
        return NanoHTTPD.newFixedLengthResponse(msg)
    }
}