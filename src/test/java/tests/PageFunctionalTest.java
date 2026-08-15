package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ExamplesOfBugsPage;
import pages.TypesOfBugsPage;
import pages.FindBugsPage;

public class PageFunctionalTest extends BaseTest {

	@Test
	public void verifyExamplesOfBugsPage(){

	    driver.get("https://academybugs.com/");

	    ExamplesOfBugsPage page =
	            new ExamplesOfBugsPage(driver);

	    Assert.assertTrue(page.isPageLoaded());

	}


	@Test
	public void verifyTypesOfBugsPage(){

	    driver.get("https://academybugs.com/types/");

	    TypesOfBugsPage page =
	            new TypesOfBugsPage(driver);

	    Assert.assertTrue(page.isPageLoaded());

	}


	@Test
	public void verifyFindBugsPage(){

	    driver.get("https://academybugs.com/find-bugs/");

	    FindBugsPage page =
	            new FindBugsPage(driver);

	    Assert.assertTrue(page.isPageLoaded());

	}

}
