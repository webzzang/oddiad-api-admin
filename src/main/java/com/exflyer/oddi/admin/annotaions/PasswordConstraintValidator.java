package com.exflyer.oddi.admin.annotaions;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.AlphabeticalSequenceRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.QwertySequenceRule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

  @Override
  public void initialize(ValidPassword arg0) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {

    int i = 0;

    if (Pattern.matches(".*[`~!@@#$%^&*|₩₩₩'₩\";:₩/?].*", password)) i++;
    if (Pattern.matches(".*[a-zA-Z].*", password)) i++;
//    if (Pattern.matches(".*[A-Z].*", password)) i++;
    if (Pattern.matches(".*[0-9].*", password)) i++;

    /*System.out.println("===================================");
    System.out.println(i);
    System.out.println(Pattern.matches(".*[0-9].*", password));
    //System.out.println(matcher.find());
    System.out.println(password);
    System.out.println("===================================");*/

    if (password.length() >= 8 && i >= 3 || password.length() >= 10 && i == 2){
      return true;
    }else{
      return false;
    }


    /*PasswordValidator validator = new PasswordValidator(Arrays.asList(
      new LengthRule(8, 30),
      new UppercaseCharacterRule(1),
      new DigitCharacterRule(1),
      new SpecialCharacterRule(1),
      new NumericalSequenceRule(3,false),
      new AlphabeticalSequenceRule(3,false),
      new QwertySequenceRule(3,false),
      new WhitespaceRule()));

    RuleResult result = validator.validate(new PasswordData(password));
    if (result.isValid()) {
      return true;
    }
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(
      Joiner.on(",").join(validator.getMessages(result)))
      .addConstraintViolation();
    return false;*/
  }

}
