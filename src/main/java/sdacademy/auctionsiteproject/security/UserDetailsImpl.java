package sdacademy.auctionsiteproject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sdacademy.auctionsiteproject.entity.Roles;
import sdacademy.auctionsiteproject.entity.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user)
    {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapRolesToAuthorities(user.getRoles());
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    } // atentie ca folosim EMAIL in loc de USERNAME!!!

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Roles> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
    public String getUserNameInfo() // intoarce NUMELE userului!!!
    {
        return user.getAccountName();
    }

    public String getUserProvince()
    {
        return user.getProvince();
    }

    public String getUserCity()
    {
        return user.getCity();
    }

    public String getUserAddress()
    {
        return user.getAddress();
    }

    public String getUserDataOfAccountCreation()
    {
        return user.getDateOfAccountCreation();
    }

    public String getUserAccountStatus()
    {
        return user.getAccountStatus();
    }

    public String getUserType()
    {
        return user.getType();
    }
}
