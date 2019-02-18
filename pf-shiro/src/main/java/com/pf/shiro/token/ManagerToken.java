package com.pf.sys.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * Author: Ru He
 * Date: Created in 2019/1/14.
 * Description:
 */
public class ManagerToken  implements AuthenticationToken, RememberMeAuthenticationToken {

    /**
     *
     */
    private static final long serialVersionUID = 6651907157015804796L;

    /**
     * The username
     */
    private String username;

    /**
     * The password, in char[] format
     */
    private char[] password;

    /**
     * Whether or not 'rememberMe' should be enabled for the corresponding login attempt;
     * default is <code>false</code>
     */
    private boolean rememberMe = false;

    public ManagerToken(final String username, final char[] password,
                        final boolean rememberMe) {

        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public ManagerToken(final String username, final char[] password) {
        this(username, password, false);
    }

    public ManagerToken(final String username, final String password, final boolean rememberMe) {
        this(username, password != null ? password.toCharArray() : null, rememberMe);
    }

    public ManagerToken(final String username, final String password) {
        this(username, password != null ? password.toCharArray() : null, false);
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return getPassword();
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void clear(){
        this.username = null;
        this.rememberMe = false;

        if (this.password != null) {
            for (int i = 0; i < password.length; i++) {
                this.password[i] = 0x00;
            }
            this.password = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(username);
        sb.append(", rememberMe=").append(rememberMe);
        return sb.toString();
    }
}