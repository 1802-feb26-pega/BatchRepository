package com.revature.bankapp.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.bankapp.util.UserInputValidation;

public class UserInputValidationTest {

	
	
	@Test
	public void testValidateNumberOnlyInputLetter() {
		assertFalse(UserInputValidation.validateNumberOnlyInput("g"));
	}
	@Test
	public void testValidateNumberOnlyInputSymbol() {
		assertFalse(UserInputValidation.validateNumberOnlyInput("/"));
	}
	@Test
	public void testValidateNumberOnlyInputToManyDigits() {
		assertFalse(UserInputValidation.validateNumberOnlyInput("123"));
	}
	@Test
	public void testValidateNumberOnlyInputCorrect() {
		assertTrue(UserInputValidation.validateNumberOnlyInput("1"));
	}

	@Test
	public void testValidateCurrencyInputCorrect() {
		assertTrue(UserInputValidation.validateCurrencyInput("25.00"));
	}
	@Test
	public void testValidateCurrencyInputNoDecimal() {
		assertTrue(UserInputValidation.validateCurrencyInput("25"));
	}
	@Test
	public void testValidateCurrencyInputNegative() {
		assertFalse(UserInputValidation.validateCurrencyInput("-25.00"));
	}
	@Test
	public void testValidateCurrencyInputLetter() {
		assertFalse(UserInputValidation.validateCurrencyInput("A25.00"));
	}
	@Test
	public void testValidateCurrencyInputToManyDecimals() {
		assertFalse(UserInputValidation.validateCurrencyInput("25.000"));
	}
	@Test
	public void testValidateCurrencyInputOnlyDecimal() {
		assertTrue(UserInputValidation.validateCurrencyInput(".00"));
	}
	@Test
	public void testValidateCurrencyInputToBigOfNmber() {
		assertFalse(UserInputValidation.validateCurrencyInput(new Double(Double.MAX_VALUE + 1).toString()));
	}

	@Test
	public void testValidateEmailInputInvalidSymbols() {
		assertFalse(UserInputValidation.validateEmailInput("ghasdjkg/@gmail.com"));
	}
	@Test
	public void testValidateEmailInputInvalidExtension() {
		assertFalse(UserInputValidation.validateEmailInput("ghasdjkg@gmail.c"));
	}
	@Test
	public void testValidateEmailInputCorrectFormat() {
		assertTrue(UserInputValidation.validateEmailInput("joseph.k.moses6@gmail.com"));
	}


	@Test
	public void testValidateNameInputInvalidSymbols() {
		assertFalse(UserInputValidation.validateNameInput(".ag"));
	}
	@Test
	public void testValidateNameInputNumber() {
		assertFalse(UserInputValidation.validateNameInput("6ag"));
	}
	@Test
	public void testValidateNameInputCorrect() {
		assertTrue(UserInputValidation.validateNameInput("ag"));
	}

	@Test
	public void testValidateAccountIdInputCorrect() {
		assertTrue(UserInputValidation.validateAccountIdInput("123"));
	}
	@Test
	public void testValidateAccountIdInputNegative() {
		assertFalse(UserInputValidation.validateAccountIdInput("-123"));
	}
	@Test
	public void testValidateAccountIdInputLetter() {
		assertFalse(UserInputValidation.validateAccountIdInput("A123"));
	}
	@Test
	public void testValidateAccountIdInputSymbol() {
		assertFalse(UserInputValidation.validateAccountIdInput("/123"));
	}

}
