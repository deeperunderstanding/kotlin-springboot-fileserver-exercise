package org.djanz.fileserver.filter

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import javax.servlet.http.HttpServletRequest

class APIKeyFilter(val keyHeader: String) : AbstractPreAuthenticatedProcessingFilter() {

    @Override
    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any {
        return request.getHeader(keyHeader)
    }

    @Override
    override fun getPreAuthenticatedCredentials(request: HttpServletRequest): Any {
        return ""
    }

}