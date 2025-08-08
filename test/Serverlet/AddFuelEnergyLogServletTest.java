package Serverlet;

import DTOs.FuelEnergyLog;
import org.junit.jupiter.api.Test;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import java.io.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AddFuelEnergyLogServletTest {

    @Test
    public void testDoPost_success() throws Exception {
        AddFuelEnergyLogServlet servlet = new AddFuelEnergyLogServlet();

        // Mock HttpServletRequest
        HttpServletRequest request = new HttpServletRequest() {
            @Override 
            public String getParameter(String name) {
                switch(name) {
                    case "vehicleId": return "1";
                    case "logDate": return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    case "fuelConsumed": return "10.5";
                    case "energyConsumed": return "5.5";
                    case "mileage": return "150.0";
                    case "notes": return "Test log";
                    default: return null; 
                }
            }
            // Implement other methods as no-op or returning null
            // ...

			@Override
			public AsyncContext getAsyncContext() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getAttribute(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getContentLength() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getContentLengthLong() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DispatcherType getDispatcherType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServletInputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getLocalAddr() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getLocalName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getLocalPort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<Locale> getLocales() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, String[]> getParameterMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getParameterNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] getParameterValues(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProtocol() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public BufferedReader getReader() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRealPath(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRemoteAddr() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRemoteHost() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getRemotePort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public RequestDispatcher getRequestDispatcher(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getScheme() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getServerName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getServerPort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isAsyncStarted() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isAsyncSupported() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void removeAttribute(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public AsyncContext startAsync() throws IllegalStateException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) throws IllegalStateException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean authenticate(HttpServletResponse arg0) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String changeSessionId() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAuthType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContextPath() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cookie[] getCookies() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getDateHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getHeaders(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getIntHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getMethod() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Part getPart(String arg0) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<Part> getParts() throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPathInfo() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPathTranslated() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getQueryString() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRemoteUser() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRequestURI() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public StringBuffer getRequestURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRequestedSessionId() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getServletPath() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HttpSession getSession() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HttpSession getSession(boolean arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Principal getUserPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isRequestedSessionIdFromCookie() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRequestedSessionIdFromURL() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRequestedSessionIdFromUrl() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRequestedSessionIdValid() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isUserInRole(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void login(String arg0, String arg1) throws ServletException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void logout() throws ServletException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <T extends HttpUpgradeHandler> T upgrade(Class<T> arg0) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}
        };

        // Mock HttpServletResponse to capture output and redirect
        final StringBuilder writerOutput = new StringBuilder();
        final String[] redirectedUrl = new String[1];

        HttpServletResponse response = new HttpServletResponse() {
            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(new Writer() {
                    @Override
                    public void write(char[] cbuf, int off, int len) {
                        writerOutput.append(cbuf, off, len);
                    }
                    @Override public void flush() {}
                    @Override public void close() {}
                });
            }

            @Override
            public void sendRedirect(String location) {
                redirectedUrl[0] = location;
            }

			@Override
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setBufferSize(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setCharacterEncoding(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setContentLength(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setContentLengthLong(long arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setContentType(String arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setLocale(Locale arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<String> getHeaders(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getStatus() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void sendError(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void sendError(int arg0, String arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setStatus(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setStatus(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

            // Implement other methods as no-op or returning null
            // ...
        };

        servlet.doPost(request, response);

        // Since in success path, it redirects
        assertEquals("ViewFuelEnergyLogsServlet", redirectedUrl[0]);
        assertEquals("", writerOutput.toString()); // no output in success path
    }

    @Test
    public void testDoPost_failure() throws Exception {
        AddFuelEnergyLogServlet servlet = new AddFuelEnergyLogServlet();

        // Request with missing vehicleId to cause failure
        HttpServletRequest request = new HttpServletRequest() {
            @Override
            public String getParameter(String name) {
                if ("vehicleId".equals(name)) return null;  // missing vehicleId
                return "";
            }
            // Implement other methods as no-op or returning null
            // ...

			@Override
			public AsyncContext getAsyncContext() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getAttribute(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getContentLength() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getContentLengthLong() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public DispatcherType getDispatcherType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ServletInputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getLocalAddr() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getLocalName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getLocalPort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<Locale> getLocales() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Map<String, String[]> getParameterMap() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getParameterNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String[] getParameterValues(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProtocol() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public BufferedReader getReader() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRealPath(String path) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRemoteAddr() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRemoteHost() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getRemotePort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public RequestDispatcher getRequestDispatcher(String path) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getScheme() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getServerName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getServerPort() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isAsyncStarted() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isAsyncSupported() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void removeAttribute(String name) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setAttribute(String name, Object o) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public AsyncContext startAsync() throws IllegalStateException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
					throws IllegalStateException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String changeSessionId() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAuthType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContextPath() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Cookie[] getCookies() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getDateHeader(String name) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getHeader(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Enumeration<String> getHeaders(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getIntHeader(String name) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getMethod() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Part getPart(String name) throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Collection<Part> getParts() throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPathInfo() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPathTranslated() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getQueryString() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRemoteUser() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRequestURI() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public StringBuffer getRequestURL() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getRequestedSessionId() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getServletPath() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HttpSession getSession() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public HttpSession getSession(boolean create) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Principal getUserPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isRequestedSessionIdFromCookie() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRequestedSessionIdFromURL() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRequestedSessionIdFromUrl() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isRequestedSessionIdValid() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isUserInRole(String role) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void login(String username, String password) throws ServletException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void logout() throws ServletException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
				return null;
			}
        };

        StringWriter responseWriter = new StringWriter();

        HttpServletResponse response = new HttpServletResponse() {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(responseWriter);
            }
            @Override
            public void sendRedirect(String location) {
                fail("Should not redirect on failure");
            }
            // Implement other methods as no-op or returning null
            // ...
			@Override
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}
			@Override
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			@Override
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setBufferSize(int size) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setCharacterEncoding(String charset) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setContentLength(int len) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setContentLengthLong(long len) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setContentType(String type) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setLocale(Locale loc) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public Collection<String> getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public Collection<String> getHeaders(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public int getStatus() {
				// TODO Auto-generated method stub
				return 0;
			}
			@Override
			public void sendError(int sc) throws IOException {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void sendError(int sc, String msg) throws IOException {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setDateHeader(String name, long date) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setHeader(String name, String value) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setIntHeader(String name, int value) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setStatus(int sc) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setStatus(int sc, String sm) {
				// TODO Auto-generated method stub
				
			}
        };

        servlet.doPost(request, response);

        String output = responseWriter.toString();
        assertTrue(output.contains("Error:"));
    }
}
