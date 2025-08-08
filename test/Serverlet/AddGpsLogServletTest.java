package Serverlet;

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
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Builder.GpsLog;
import DTOs.UserDTO;
import DaoImpl.GpsLogImp;
import DaoImpl.VehicleDaoImp;
import model.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

public class AddGpsLogServletTest {

    // Simple manual mock HttpServletRequest
    static class MockHttpServletRequest extends HttpServletRequestWrapper {
        private HttpSession session;
        private String dispatcherPath;
        private Object setAttributeKey;
        private Object setAttributeValue;

        public MockHttpServletRequest() {
            super(new HttpServletRequest() {

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
				public String getParameter(String name) {
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
                // Empty base, override methods as needed
            });
        }

        public void setSession(HttpSession session) {
            this.session = session;
        }

        @Override
        public HttpSession getSession(boolean create) {
            return session;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            dispatcherPath = path;
            return new MockRequestDispatcher(path);
        }

        @Override
        public void setAttribute(String key, Object value) {
            setAttributeKey = key;
            setAttributeValue = value;
        }

        @Override
        public Object getAttribute(String name) {
            if (name.equals(setAttributeKey)) return setAttributeValue;
            return null;
        }

        public String getDispatchedPath() {
            return dispatcherPath;
        }
    }

    // Simple manual mock HttpSession
    static class MockHttpSession implements HttpSession {
        private Object user;

        public void setUser(Object user) {
            this.user = user;
        }

        @Override
        public Object getAttribute(String name) {
            if ("user".equals(name)) return user;
            return null;
        }

        // Other HttpSession methods can be no-op or throw UnsupportedOperationException
        // For simplicity, only override what you use

        @Override public long getCreationTime() { return 0; }
        @Override public String getId() { return "mock-session"; }
        @Override public long getLastAccessedTime() { return 0; }
        @Override public ServletContext getServletContext() { return null; }
        @Override public void setMaxInactiveInterval(int interval) {}
        @Override public int getMaxInactiveInterval() { return 0; }
        @Override public HttpSessionContext getSessionContext() { return null; }
        @Override public Object getValue(String name) { return null; }
        @Override public Enumeration<String> getAttributeNames() { return null; }
        @Override public String[] getValueNames() { return new String[0]; }
        @Override public void setAttribute(String name, Object value) {}
        @Override public void putValue(String name, Object value) {}
        @Override public void removeAttribute(String name) {}
        @Override public void removeValue(String name) {}
        @Override public void invalidate() {}
        @Override public boolean isNew() { return false; }
    }

    // Simple manual mock HttpServletResponse
    static class MockHttpServletResponse extends HttpServletResponseWrapper {
        private String redirectLocation = null;
        private StringWriter writer = new StringWriter();

        public MockHttpServletResponse() {
            super(new HttpServletResponse() {

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
				public PrintWriter getWriter() throws IOException {
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
				public void addCookie(Cookie cookie) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void addDateHeader(String name, long date) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void addHeader(String name, String value) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void addIntHeader(String name, int value) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public boolean containsHeader(String name) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public String encodeRedirectURL(String url) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String encodeRedirectUrl(String url) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String encodeURL(String url) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String encodeUrl(String url) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String getHeader(String name) {
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
				public void sendRedirect(String location) throws IOException {
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
                // base no-op
            });
        }

        @Override
        public void sendRedirect(String location) {
            redirectLocation = location;
        }

        public String getRedirectLocation() {
            return redirectLocation;
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(writer);
        }

        public String getOutput() {
            return writer.toString();
        }
    }

    // Simple manual mock RequestDispatcher
    static class MockRequestDispatcher implements RequestDispatcher {
        private String path;
        boolean forwarded = false;

        public MockRequestDispatcher(String path) {
            this.path = path;
        }

        @Override
        public void forward(ServletRequest request, ServletResponse response) {
            forwarded = true;
        }

        @Override
        public void include(ServletRequest request, ServletResponse response) {
        }

        public boolean wasForwarded() {
            return forwarded;
        }

        public String getPath() {
            return path;
        }

		@Override
		public void forward(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void include(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		}
    }


    @Test
    public void testDoGetWithUser() throws ServletException, IOException {
        AddGpsLogServlet servlet = new AddGpsLogServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockHttpSession session = new MockHttpSession();

        // Add a user to session
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setName("Test User");
        session.setUser(user);

        req.setSession(session);

        // Call doGet
        servlet.doGet(req, res);

        // Assert request dispatched to gpslog.jsp
        assertEquals("gpslog.jsp", req.getDispatchedPath());

        // Assert user attribute was set (vehicles and logs should be non-null)
        assertNotNull(req.getAttribute("vehicles"));
        assertNotNull(req.getAttribute("logs"));
    }

    @Test
    public void testDoGetWithoutUser() throws ServletException, IOException {
        AddGpsLogServlet servlet = new AddGpsLogServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        // No session / no user set
        req.setSession(null);

        // Call doGet
        servlet.doGet(req, res);

        // Assert redirect to login.jsp
        assertEquals("login.jsp", res.getRedirectLocation());
    }
}
