package saracoglu.saracoglu_lab_02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HelloController {

    @FXML
    private Label annIntLbl;

    @FXML
    private TextArea annIntRateTA;

    @FXML
    private Label loanAmountLbl;

    @FXML
    private TextArea loanAmtTA;

    @FXML
    private Label monthlyPayLbl;

    @FXML
    private TextArea monthlyPayTA;

    @FXML
    private Label numYearsLbl;

    @FXML
    private TextArea numYearsTA;

    @FXML
    private Label totalPayLbl;

    @FXML
    private TextArea totalPayTA;

    @FXML
    void calculatePayment(ActionEvent event) {
        //Parse user input text values -> Lacks validation for incorrect inputs
        //Convert annIntRate% to an appropriate double value for int rate, divide by 100
        double annIntRate = Double.parseDouble(annIntRateTA.getText()) / 100;

        //Number of years
        int numYears = Integer.parseInt(numYearsTA.getText());

        //Moved monthly pay calculation to separate method for readability
        double monthlyPay = getMonthlyPay(annIntRate, numYears);
        //total = monthlyPay * numYears * 12 (months in a year)
        double totalPay = (monthlyPay) * (numYears) * (12);

        //Establish BigDecimal to preserve values of 2 decimal spaces after for change
        //Half_Even is used in banking?
        //Save rounding until calculations are complete
        BigDecimal bdMonthlyPay = new BigDecimal(Double.toString(monthlyPay));
        bdMonthlyPay = bdMonthlyPay.setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal bdTotalPay = new BigDecimal(Double.toString(totalPay));
        bdTotalPay = bdTotalPay.setScale(2, RoundingMode.HALF_EVEN);

        //Round payment values for display
        totalPay = bdTotalPay.doubleValue();
        monthlyPay = bdMonthlyPay.doubleValue();

        //Display Rounded totals to user
        monthlyPayTA.setText("$" + monthlyPay);
        totalPayTA.setText("$" + totalPay);
    }

    //Monthly payment calculations
    private double getMonthlyPay(double annIntRate, int numYears) {
        double loanAmount = Double.parseDouble(loanAmtTA.getText());
        double monthlyIntRate = (annIntRate / 12);

        double monthlyPay = (loanAmount * monthlyIntRate)
                                / ( 1 - 1 / Math.pow(1 + monthlyIntRate, numYears * 12));
        return monthlyPay;
    }

}
