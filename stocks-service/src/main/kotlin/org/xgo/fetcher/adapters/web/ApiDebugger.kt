package org.xgo.fetcher.adapters.web

import org.jboss.logging.Logger
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientResponseContext
import javax.ws.rs.client.ClientResponseFilter


class ApiDebugger: ClientResponseFilter {

    private val LOG: Logger = Logger.getLogger(ApiDebugger::class.java)

    override fun filter(requestContext: ClientRequestContext?, responseContext: ClientResponseContext?) {
        LOG.info(requestContext?.uri.toString().replace(Regex("[A-Z0-9]{16}"), "redacted"))
    }
}
