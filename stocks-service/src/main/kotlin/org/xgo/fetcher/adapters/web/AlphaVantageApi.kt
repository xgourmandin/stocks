package org.xgo.fetcher.adapters.web

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.annotations.jaxrs.QueryParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Response

enum class OutputSize(val size: String) {
    COMPACT("compact"),
    FULL("full");

    override fun toString(): String {
        return size
    }
}

@Path("/query")
@RegisterRestClient
@RegisterProvider(ApiDebugger::class)
interface AlphaVantageApi {

    @GET
    fun getHistoricData(
        @QueryParam symbol: String,
        @QueryParam outputsize: OutputSize,
        @QueryParam apikey: String,
        @QueryParam function: String = "TIME_SERIES_DAILY"
    ): Response
}
