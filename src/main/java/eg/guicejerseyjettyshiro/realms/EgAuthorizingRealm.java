package eg.guicejerseyjettyshiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class EgAuthorizingRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		System.out.println("In EgAuthorizingRealm.doGetAuthenticationInfo for: " + upToken.getUsername() + "/" + new String(upToken.getPassword()) + " - remember=" + upToken.isRememberMe());
		return new SimpleAuthenticationInfo(upToken.getUsername(), upToken.getPassword(), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("In EgAuthorizingRealm.doGetAuthorizationInfo");
		// Doing nothing just now
		return null;
	}

}
