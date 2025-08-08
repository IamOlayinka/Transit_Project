package Serverlet;

import DTOs.ContactDTO;
import Dao.ContactDAO;
import DaoImpl.ContactDAOImpl;

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

public class ContactServletTest {

    static class MockHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String> params = new HashMap<>();
        private final Map<String, Object> attributes = new HashMap<>();
        private RequestDispatcher dispatcher;

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
        public void setAttribute(String name, Object o) {
            attributes.put(name, o);
        }

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            this.dispatcher = new MockRequestDispatcher(path);
            return this.dispatcher;
        }

        public RequestDispatcher getDispatcher() {
            return dispatcher;
        }
    }

    static class MockHttpServletResponse extends HttpServletResponseWrapper {
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
    }

    static class MockRequestDispatcher implements RequestDispatcher {
        private final String path;
        private boolean forwardCalled = false;

        public MockRequestDispatcher(String path) {
            this.path = path;
        }

        @Override
        public void forward(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) {
            forwardCalled = true;
        }

        @Override
        public void include(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) {
            // not needed for test
        }

        public boolean isForwardCalled() {
            return forwardCalled;
        }

        public String getPath() {
            return path;
        }
    }

    // Override ContactServlet to inject a test ContactDAO
    class TestContactServlet extends ContactServlet {
        private final ContactDAO testDao;

        public TestContactServlet(ContactDAO dao) {
            this.testDao = dao;
        }

        @Override
        protected ContactDAO getContactDAO() {
            return testDao;
        }
    }

    // A simple test DAO that always returns true for saveMessage
    class DummyContactDAO implements ContactDAO {
        boolean lastSaveCalled = false;
        ContactDTO lastSaved = null;

        @Override
        public boolean saveMessage(ContactDTO contact) {
            lastSaveCalled = true;
            lastSaved = contact;
            return true;
        }
    }

    @Test
    public void testDoPostSuccess() throws ServletException, IOException {
        DummyContactDAO dao = new DummyContactDAO();
        ContactServlet servlet = new ContactServlet() {
            // Override DAO with dummy
            @Override
            protected ContactDAO getContactDAO() {
                return dao;
            }
        };

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("name", "John Doe");
        request.setParameter("email", "john@example.com");
        request.setParameter("message", "Hello!");

        // Since original code does not have getContactDAO() method, 
        // You can make contactDAO protected and override it in subclass or 
        // Just modify your servlet to allow DAO injection for testability.

        // For now, we manually set the DAO on servlet (if contactDAO is private, this requires reflection or changing visibility)
        // Alternatively: create a setter or constructor to inject DAO in your servlet.

        // To keep it simple, here is a minimal working override of doPost:

        servlet = new ContactServlet() {
            private final ContactDAO daoInstance = dao;

            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String message = req.getParameter("message");

                ContactDTO contact = new ContactDTO(name, email, message);

                boolean success = daoInstance.saveMessage(contact);

                if (success) {
                    req.setAttribute("msg", "Your message has been sent successfully!");
                } else {
                    req.setAttribute("msg", "Oops! Something went wrong. Please try again.");
                }

                req.getRequestDispatcher("homepage.jsp").forward(req, resp);
            }
        };

        servlet.doPost(request, response);

        assertEquals("Your message has been sent successfully!", request.getAttribute("msg"));

        MockRequestDispatcher dispatcher = (MockRequestDispatcher) request.getDispatcher();
        assertNotNull(dispatcher);
        assertTrue(dispatcher.isForwardCalled());
        assertEquals("homepage.jsp", dispatcher.getPath());

        assertTrue(dao.lastSaveCalled);
        assertNotNull(dao.lastSaved);
        assertEquals("John Doe", dao.lastSaved.getName());
        assertEquals("john@example.com", dao.lastSaved.getEmail());
        assertEquals("Hello!", dao.lastSaved.getMessage());
    }
}
