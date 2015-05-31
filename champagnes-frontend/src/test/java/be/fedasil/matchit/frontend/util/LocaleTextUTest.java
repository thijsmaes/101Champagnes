package be.fedasil.matchit.frontend.util;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class LocaleTextUTest {

	@Test
	public void testGetCaptionOK()
	{
		Assert.assertEquals("Faut", LocaleText.getCaption("notification.error",Locale.FRANCE));
	}
	@Test
	public void testGetCaptionMissing()
	{
		Assert.assertEquals("!MISSING[faultmessagecode]",LocaleText.getCaption("faultmessagecode",Locale.FRANCE));
	}
}
