package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class CheckoutDataProvider {

    @DataProvider(name = "checkoutData")
    public static Object[][] getCheckoutData()
            throws IOException {

        String filePath =
            System.getProperty("user.dir")
            + "/src/test/resources/testdata/"
            + "CheckoutData.xlsx";

        System.out.println(
            "Reading Excel from: "
            + filePath
        );

        return ExcelUtils.getExcelDataAsMap(
            filePath,
            "CheckoutData"
        );
    }
}