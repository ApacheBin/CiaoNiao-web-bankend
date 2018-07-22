package com.cainiaoshixi.validation.validator;

import com.cainiaoshixi.validation.constraint.EnterpriseMail;
import org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator;

import javax.validation.ConstraintValidatorContext;

/**
 * 企业邮箱验证器
 */
public class EnterpriseMailValidator extends AbstractEmailValidator<EnterpriseMail> {

    private static final String[] NOT_ENTERPRISE_EMAIL_DOMAIN_LIST = {
            "qq.com", "163.com", "gmail.com" ,
            "yahoo.com", "msn.com", "hotmail.com",
            "aol.com", "@sk.com", "live.com",
            "0355.net", "163.net", "263.net",
            "3721.net", "yeah.net", "googlemail.com",
            "mail.com", "yahoo.com", "126.com",
            "sogou.com", "sina.com"
    };

    @Override
    public void initialize(EnterpriseMail constraintAnnotation) {

    }

    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if ( value == null || value.length() == 0 ) {
            return false;
        }

//        if (!super.isValid(value, context)) {
//            return false;
//        }

        String stringValue = value.toString().trim();
        int splitPosition = stringValue.lastIndexOf( '@' );

        String domainPart = stringValue.substring( splitPosition + 1 );

        for (String domain : NOT_ENTERPRISE_EMAIL_DOMAIN_LIST) {
            if (domainPart.equals(domain))
                return false;
        }
        return true;
    }

}
