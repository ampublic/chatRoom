package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import responseWrapper.GZipResponseWrapper;

public class GZipFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request  = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		String acceptEncoding = request.getHeader("Accept-Encoding");
		if (acceptEncoding != null && acceptEncoding.toLowerCase().indexOf("gzip") != -1) {
			
			GZipResponseWrapper gzipRes = new GZipResponseWrapper(response);
			
			chain.doFilter(request, gzipRes);
			gzipRes.finishResponse();
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}