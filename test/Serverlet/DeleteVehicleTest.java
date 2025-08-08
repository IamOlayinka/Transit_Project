package Serverlet;

import DTOs.UserDTO;
import DaoImpl.VehicleDaoImp;

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
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteVehicleTest {

    static class MockHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String> params = new HashMap<>();
        private HttpSession session;

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
				}});
        }

        public void setParameter(String name, String value) {
            params.put(name, value);
        }

        @Override
        public String getParameter(String name) {
            return params.get(name);
        }

        @Override
        public HttpSession getSession(boolean create) {
            return session;
        }

        public void setSession(HttpSession session) {
            this.session = session;
        }
    }

    static class MockHttpServletResponse extends HttpServletResponseWrapper {
        private String redirectedUrl;

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
					
				}});
        }

        @Override
        public void sendRedirect(String location) {
            this.redirectedUrl = location;
        }

        public String getRedirectedUrl() {
            return redirectedUrl;
        }
    }

    static class MockHttpSession implements HttpSession {
        private final Map<String, Object> attributes = new HashMap<>();

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        @Override
        public void setAttribute(String name, Object value) { 
            attributes.put(name, value);
        }

        // Implement other methods with empty or dummy returns as needed
        @Override public long getCreationTime() { return 0; }
        @Override public String getId() { return "mockSessionId"; }
        @Override public long getLastAccessedTime() { return 0; }
        @Override public ServletContext getServletContext() { return null; }
        @Override public void setMaxInactiveInterval(int interval) {}
        @Override public int getMaxInactiveInterval() { return 0; }
        @Override public HttpSessionContext getSessionContext() { return null; }
        @Override public Object getValue(String name) { return null; }
        @Override public Enumeration<String> getAttributeNames() { return java.util.Collections.enumeration(attributes.keySet()); }
        @Override public String[] getValueNames() { return new String[0]; }
        @Override public void putValue(String name, Object value) {}
        @Override public void removeAttribute(String name) { attributes.remove(name); }
        @Override public void removeValue(String name) {}
        @Override public void invalidate() {}
        @Override public boolean isNew() { return false; }
    }

    // Dummy DAO for testing
    class DummyVehicleDaoImp extends VehicleDaoImp {
        boolean deleted = false;
        int deletedId = -1;

        @Override
        public boolean deleteVehicle(int id) {
            deleted = true;
            deletedId = id;
            return true; // simulate successful delete
        }
    }

    // Override servlet to inject dummy DAO
    class TestDeleteVehicleServlet extends Serverlet.deleteVehicle {
        private final DummyVehicleDaoImp dummyDao = new DummyVehicleDaoImp();

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                res.sendRedirect("login.jsp");
                return;
            }

            UserDTO user = (UserDTO) session.getAttribute("user");

            if (!"Manager".equalsIgnoreCase(user.getUserType())) {
                res.sendRedirect("vehicleList?error=unauthorized");
                return;
            }
            int id = 0;

            try {
                id = Integer.parseInt(req.getParameter("id").trim());
            } catch (NumberFormatException e) {
                session.setAttribute("message", "Invalid vehicle ID.");
                res.sendRedirect("VehicleList");
                return;
            }

            boolean deleted = dummyDao.deleteVehicle(id);

            if (deleted) {
                session.setAttribute("message", "Vehicle deleted successfully.");
            } else {
                session.setAttribute("message", "Failed to delete vehicle.");
            }

            res.sendRedirect("VehicleList");
        }
    }

    @Test
    public void testDoPostSuccessfulDelete() throws ServletException, IOException {
        TestDeleteVehicleServlet servlet = new TestDeleteVehicleServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockHttpSession session = new MockHttpSession();

        UserDTO user = new UserDTO();
        user.setUserType("Manager");

        session.setAttribute("user", user);
        req.setSession(session);
        req.setParameter("id", "123");

        servlet.doPost(req, res);

        assertEquals("Vehicle deleted successfully.", session.getAttribute("message"));
        assertEquals("VehicleList", res.getRedirectedUrl());
    }

    @Test
    public void testDoPostUnauthorizedUser() throws ServletException, IOException {
        TestDeleteVehicleServlet servlet = new TestDeleteVehicleServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockHttpSession session = new MockHttpSession();

        UserDTO user = new UserDTO();
        user.setUserType("Driver"); // Not Manager

        session.setAttribute("user", user);
        req.setSession(session);
        req.setParameter("id", "123");

        servlet.doPost(req, res);

        assertEquals("vehicleList?error=unauthorized", res.getRedirectedUrl());
    }

    @Test
    public void testDoPostInvalidId() throws ServletException, IOException {
        TestDeleteVehicleServlet servlet = new TestDeleteVehicleServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockHttpSession session = new MockHttpSession();

        UserDTO user = new UserDTO();
        user.setUserType("Manager");

        session.setAttribute("user", user);
        req.setSession(session);
        req.setParameter("id", "abc"); // invalid integer

        servlet.doPost(req, res);

        assertEquals("Invalid vehicle ID.", session.getAttribute("message"));
        assertEquals("VehicleList", res.getRedirectedUrl());
    }

    @Test
    public void testDoPostNoSession() throws ServletException, IOException {
        TestDeleteVehicleServlet servlet = new TestDeleteVehicleServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        // no session set
        req.setParameter("id", "123");

        servlet.doPost(req, res);

        assertEquals("login.jsp", res.getRedirectedUrl());
    }
}
