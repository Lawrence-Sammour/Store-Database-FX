import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

public class OnlineStoreDB extends Application {

	private String currentSelection = "";
	private String operationSelection;

	public static void main(String[] args) {

		launch(args);

	}

	private VBox mainBar = new VBox();

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Database_Lawrence");

		BorderPane fp = new BorderPane();
		Text welcome = new Text();
		welcome.setText("WELCOME TO MY DATABASE");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		welcome.setFill(Color.WHITE);
		fp.setTop(welcome);
		BorderPane.setAlignment(welcome, Pos.CENTER);

		VBox tables = new VBox();
		tables.setSpacing(12);
		
		// Tables
		Button customers = new Button();
		customers.setText("customers");
		customers.setMinSize(100, 70);
		customers.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView(fp, "Select * From customers");

			}
		});

		////////////////////////////////////////////
		Button employees = new Button();
		employees.setText("employees");
		employees.setMinSize(100, 70);

		employees.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView(fp, "select *from employees");
			}

		});

		//////////////////////////////////////////////////
		Button orderDetails = new Button();
		orderDetails.setText("order Details");
		orderDetails.setMinSize(100, 70);

		orderDetails.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tableView(fp, "select *from orderDetails");
			}

		});
		/////////////////////////////////////////
		Button productlines = new Button();
		productlines.setText("Product lines");
		productlines.setMinSize(100, 70);
		productlines.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tableView(fp, "select * from productLines");
			}

		});

		////////////////////////////////////////////////
		Button orders = new Button();
		orders.setText("orders");
		orders.setMinSize(100, 70);

		orders.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tableView(fp, "select *from orders");

			}

		});

		//////////////////////////////////////////////////////
		Button offices = new Button();
		offices.setText("offices");
		offices.setMinSize(100, 70);
		offices.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tableView(fp, "select *from offices");
			}
		});

		//////////////////////////////////////////////////////////
		Button products = new Button();
		products.setText("products");
		products.setMinSize(100, 70);
		products.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tableView(fp, "Select * from products");
			}
		});

		/////////////////////////////////////////////////
		Button payments = new Button();
		payments.setText("payments");
		payments.setMinSize(100, 70);
		payments.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tableView(fp, "select *from payments");
			}

		});

		// about
		Menu abtM = new Menu("Info");
		MenuItem sec1 = new MenuItem("Database Info");
		MenuItem sec2 = new MenuItem("Creator Info");
		abtM.getItems().add(sec1);
		abtM.getItems().add(sec2);

		sec1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				Text abt = new Text();
				abt.setText(
						"   This database is designed to store information about an online store. it has 8 tables in total and each table has between 4-13\n\n"
								+ "   attributes. The 8 tables are products, payments,orders,offices,employees,productlines, orderDetails and customers. The customers\n\n"
								+ "   table is connected by foreign keys with three tables (orders, payments, employees) and products is connected with orderDetails\n\n"
								+ "   which is a relation between order and products while employees is connected with offices. Keys:\n\n\n\n"
								+ "   -Poducts: productCode(primary key)\n\n"
								+ "   -ProductLines: productLine(primaryKey)\n\n"
								+ "   -Employees: employeeNumber(primaryKey)\n\n"
								+ "   -offices: officeCode(primaryKey)\n\n"
								+ "   -Customers: customerNumber(PrimaryKey)\n\n"
								+ "   -orderDetails: orderNumber(foreign Key), productCode(foreign key)\n\n"
								+ "   -orders: orderNumber(primary key)\n\n"
								+ "   -Payments: customerNumber(foreign Key),checkNumber(primaryKey)");
				abt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
				abt.setFill(Color.WHITE);
				abt.setTextAlignment(TextAlignment.CENTER);
				fp.setCenter(abt);
			}
		});

		sec2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				Text abt2 = new Text();
				abt2.setText("Dony By Lawrence Sammour\n\n\n\n\n" + "StudentID: 201904605");
				abt2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
				abt2.setFill(Color.WHITE);
				abt2.setTextAlignment(TextAlignment.CENTER);
				fp.setCenter(abt2);
			}
		});

		// table of search //
		VBox operations = new VBox();

		////////////////////// customer Vbox////////////////////////////

		Label custNum = new Label();
		custNum.setText("Number");
		TextField customerNum = new TextField();

		Label custName = new Label();
		custName.setText("Name");
		TextField customerName = new TextField();

		Label city = new Label();
		city.setText("City");
		TextField cityN = new TextField();

		Label country = new Label();
		country.setText("Country");
		TextField countryN = new TextField();

		Label salesRepEmp = new Label();
		salesRepEmp.setText("SalesRepNum");
		TextField salesRep = new TextField();

		Label credit = new Label();
		credit.setText("credit Limit");
		TextField creditL = new TextField();

		HBox custBox1 = new HBox();
		custBox1.getChildren().addAll(custNum, customerNum);

		HBox custBox2 = new HBox();
		custBox2.getChildren().addAll(custName, customerName);

		HBox custBox3 = new HBox();
		custBox3.getChildren().addAll(city, cityN);

		HBox custBox4 = new HBox();
		custBox4.getChildren().addAll(country, countryN);

		HBox custBox5 = new HBox();
		custBox5.getChildren().addAll(salesRepEmp, salesRep);

		HBox custBox6 = new HBox();
		custBox6.getChildren().addAll(credit, creditL);

		HBox customersBoxes = new HBox();
		customersBoxes.getChildren().addAll(custBox1, custBox2, custBox3, custBox4, custBox5, custBox6);
		customersBoxes.setSpacing(40);

		/////////////////order details menu bar/////////////////////////////

		Label orderNumber = new Label();
		orderNumber.setText("orderNumber");
		TextField orderNum = new TextField();

		Label productCode = new Label();
		productCode.setText("productCode");
		TextField productC = new TextField();

		Label quantity = new Label();
		quantity.setText("Quantity Ordered");
		TextField quantityO = new TextField();

		Label priceEach = new Label();
		priceEach.setText("Price");
		TextField eachPrice = new TextField();

		Label orderLineNum = new Label();
		orderLineNum.setText("Line Number");
		TextField lineNum = new TextField();

		HBox details1 = new HBox();
		details1.getChildren().addAll(orderNumber, orderNum);

		HBox details2 = new HBox();
		details2.getChildren().addAll(productCode, productC);

		HBox details3 = new HBox();
		details3.getChildren().addAll(quantity, quantityO);

		HBox details4 = new HBox();
		details4.getChildren().addAll(priceEach, eachPrice);

		HBox details5 = new HBox();
		details5.getChildren().addAll(orderLineNum, lineNum);

		HBox orderDetailsBoxes = new HBox();
		orderDetailsBoxes.getChildren().addAll(details1, details2, details3, details4, details5);
		orderDetailsBoxes.setSpacing(72);

		/////////// product Lines menubar //////////////////////////

		Label productLine = new Label();
		productLine.setText("Product Line");
		TextField productL = new TextField();

		Label textDescription = new Label();
		textDescription.setText("Description");
		TextField textD = new TextField();

		HBox productlines1 = new HBox();
		productlines1.getChildren().addAll(productLine, productL);

		HBox productlines2 = new HBox();
		productlines2.getChildren().addAll(textDescription, textD);

		HBox productlinesBoxes = new HBox();
		productlinesBoxes.getChildren().addAll(productlines1, productlines2);
		productlinesBoxes.setSpacing(100);

		/////////////////////// employees menubar ////////////////////////////////////

		Label employeeNumber = new Label();
		employeeNumber.setText("employee Number");
		TextField employeeNum = new TextField();

		Label lastName = new Label();
		lastName.setText("last Name");
		TextField lastN = new TextField();

		Label firstName = new Label();
		firstName.setText("First Name");
		TextField firstN = new TextField();

		Label reportsTo = new Label();
		reportsTo.setText("reportsTo");
		TextField reports = new TextField();

		Label jobTitle = new Label();
		jobTitle.setText("job Title");
		TextField jobT = new TextField();

		HBox employees1 = new HBox();
		employees1.getChildren().addAll(employeeNumber, employeeNum);

		HBox employees2 = new HBox();
		employees2.getChildren().addAll(lastName, lastN);

		HBox employees3 = new HBox();
		employees3.getChildren().addAll(firstName, firstN);

		HBox employees4 = new HBox();
		employees4.getChildren().addAll(reportsTo, reports);

		HBox employees5 = new HBox();
		employees5.getChildren().addAll(jobTitle, jobT);

		HBox employeesBox = new HBox();
		employeesBox.getChildren().addAll(employees1, employees2, employees3, employees4, employees5);
		employeesBox.setSpacing(80);

		/////////////////// offices/////////////////////////////

		Label officeCode = new Label();
		officeCode.setText("office Code");
		TextField officeC = new TextField();

		Label officeCity = new Label();
		officeCity.setText("Office City");
		TextField cityOffice = new TextField();

		Label officeState = new Label();
		officeState.setText("office state");
		TextField officeS = new TextField();

		Label officeCountry = new Label();
		officeCountry.setText("office Country");
		TextField countryOffice = new TextField();

		HBox offices1 = new HBox();
		offices1.getChildren().addAll(officeCode, officeC);

		HBox offices2 = new HBox();
		offices2.getChildren().addAll(officeCity, cityOffice);

		HBox offices3 = new HBox();
		offices3.getChildren().addAll(officeState, officeS);

		HBox offices4 = new HBox();
		offices4.getChildren().addAll(officeCountry, countryOffice);

		HBox officeBoxes = new HBox();
		officeBoxes.getChildren().addAll(offices1, offices2, offices3, offices4);
		officeBoxes.setSpacing(80);

		////////////////////////// Products/////////////////////

		Label productCode2 = new Label();
		productCode2.setText("product code");
		TextField productC2 = new TextField();

		Label productName = new Label();
		productName.setText("product Name");
		TextField productN = new TextField();

		Label productLine2 = new Label();
		productLine2.setText("product Line");
		TextField productL2 = new TextField();

		Label productDescription = new Label();
		productDescription.setText("Description");
		TextField productD = new TextField();

		Label quantityP = new Label();
		quantityP.setText("quantity");
		TextField productQ = new TextField();

		Label buyPrice = new Label();
		buyPrice.setText("Price");
		TextField buyP = new TextField();

		HBox products1 = new HBox();
		products1.getChildren().addAll(productCode2, productC2);

		HBox products2 = new HBox();
		products2.getChildren().addAll(productName, productN);

		HBox products3 = new HBox();
		products3.getChildren().addAll(productDescription, productD);

		HBox products4 = new HBox();
		products4.getChildren().addAll(quantityP, productQ);

		HBox products5 = new HBox();
		products5.getChildren().addAll(productLine2, productL2);

		HBox products6 = new HBox();
		products6.getChildren().addAll(buyPrice, buyP);

		HBox productBox = new HBox();
		productBox.getChildren().addAll(products1, products2, products3, products4, products5, products6);
		productBox.setSpacing(25);

		////////////////////// payments menu item/////////////////////////

		Label customerNumber = new Label();
		customerNumber.setText("customer number");
		TextField customerNum2 = new TextField();

		Label paymentDate = new Label();
		paymentDate.setText("payment date");
		TextField paymentD = new TextField();

		Label amount = new Label();
		amount.setText("amount");
		TextField amount2 = new TextField();

		HBox payments1 = new HBox();
		payments1.getChildren().addAll(customerNumber, customerNum2);

		HBox payments2 = new HBox();
		payments2.getChildren().addAll(paymentDate, paymentD);

		HBox payments3 = new HBox();
		payments3.getChildren().addAll(amount, amount2);

		HBox paymentBoxes = new HBox();
		paymentBoxes.getChildren().addAll(payments1, payments2, payments3);
		paymentBoxes.setSpacing(72);

		////////////////////////// orders menu item//////////////

		Label orderNumber2 = new Label();
		orderNumber2.setText("order number");
		TextField orderNum2 = new TextField();

		Label orderDate = new Label();
		orderDate.setText("order date");
		TextField orderD = new TextField();

		Label customerNumber2 = new Label();
		customerNumber2.setText("customer Number");
		TextField customerNum3 = new TextField();

		HBox orders1 = new HBox();
		orders1.getChildren().addAll(orderNumber2, orderNum2);

		HBox orders2 = new HBox();
		orders2.getChildren().addAll(orderDate, orderD);

		HBox orders3 = new HBox();
		orders3.getChildren().addAll(customerNumber2, customerNum3);

		HBox orderBoxes = new HBox();
		orderBoxes.getChildren().addAll(orders1, orders2, orders3);
		orderBoxes.setSpacing(72);

		//////////////////////////////////////////////////////////////// INSERT//////////////////////////////////////////////////////////////////////////////////

		///////////////////////////// payments insert//////////////////////

		Label customerNumberPayments = new Label();
		customerNumberPayments.setText("customer number");
		TextField customerNum2Payments = new TextField();

		Label paymentDatePayments = new Label();
		paymentDatePayments.setText("payment date");
		TextField paymentDPayments = new TextField();

		Label amountPayments = new Label();
		amountPayments.setText("amount");
		TextField amount2Payments = new TextField();

		Label checkNumberPayments = new Label();
		checkNumberPayments.setText("check Number");
		TextField checkNumPayments = new TextField();

		HBox payments1Insert = new HBox();
		payments1Insert.getChildren().addAll(customerNumberPayments, customerNum2Payments);

		HBox payments2Insert = new HBox();
		payments2Insert.getChildren().addAll(paymentDatePayments, paymentDPayments);

		HBox payments3Insert = new HBox();
		payments3Insert.getChildren().addAll(amountPayments, amount2Payments);

		HBox payments4Insert = new HBox();
		payments4Insert.getChildren().addAll(checkNumberPayments, checkNumPayments);

		HBox paymentBoxesInsert1 = new HBox();
		paymentBoxesInsert1.getChildren().addAll(payments1Insert, payments2Insert);
		paymentBoxesInsert1.setSpacing(72);

		HBox paymentBoxesInsert2 = new HBox();
		paymentBoxesInsert2.getChildren().addAll(payments3Insert, payments4Insert);
		paymentBoxesInsert2.setSpacing(72);

		HBox paymentBoxesInsert = new HBox();
		paymentBoxesInsert.getChildren().addAll(paymentBoxesInsert1, paymentBoxesInsert2);
		paymentBoxesInsert.setSpacing(72);

		//////////////////////////////// customers INSERT////////////////

		Label custNumCustomers = new Label();
		custNumCustomers.setText("Number");
		TextField customerNumCustomers = new TextField();

		Label custNameCustomers = new Label();
		custNameCustomers.setText("Name");
		TextField customerNameCustomers = new TextField();

		Label cityCustomers = new Label();
		cityCustomers.setText("City");
		TextField cityNCustomers = new TextField();

		Label countryCustomers = new Label();
		countryCustomers.setText("Country");
		TextField countryNCustomers = new TextField();

		Label salesRepEmpCustomers = new Label();
		salesRepEmpCustomers.setText("SalesRepNum");
		TextField salesRepCustomers = new TextField();

		Label creditCustomers = new Label();
		creditCustomers.setText("credit Limit");
		TextField creditLCustomers = new TextField();

		HBox custBox1Insert = new HBox();
		custBox1Insert.getChildren().addAll(custNumCustomers, customerNumCustomers);

		HBox custBox2Insert = new HBox();
		custBox2Insert.getChildren().addAll(custNameCustomers, customerNameCustomers);

		HBox custBox3Insert = new HBox();
		custBox3Insert.getChildren().addAll(cityCustomers, cityNCustomers);

		HBox custBox4Insert = new HBox();
		custBox4Insert.getChildren().addAll(countryCustomers, countryNCustomers);

		HBox custBox5Insert = new HBox();
		custBox5Insert.getChildren().addAll(salesRepEmpCustomers, salesRepCustomers);

		HBox custBox6Insert = new HBox();
		custBox6Insert.getChildren().addAll(creditCustomers, creditLCustomers);

		HBox customersBoxesInsert1 = new HBox();
		customersBoxesInsert1.getChildren().addAll(custBox1Insert, custBox2Insert, custBox3Insert);
		customersBoxesInsert1.setSpacing(25);

		HBox customersBoxesInsert2 = new HBox();
		customersBoxesInsert2.getChildren().addAll(custBox4Insert, custBox5Insert, custBox6Insert);
		customersBoxesInsert2.setSpacing(25);

		HBox customersBoxesInsert = new HBox();
		customersBoxesInsert.getChildren().addAll(customersBoxesInsert1, customersBoxesInsert2);
		customersBoxesInsert.setSpacing(25);

		/////////////////////////////////////////////////// Employess
		/////////////////////////////////////////////////// Insert//////////////

		Label employeeNumberEmployees = new Label();
		employeeNumberEmployees.setText("employee Number");
		TextField employeeNumEmployees = new TextField();

		Label lastNameEmployees = new Label();
		lastNameEmployees.setText("last Name");
		TextField lastNEmployees = new TextField();

		Label firstNameEmployees = new Label();
		firstNameEmployees.setText("First Name");
		TextField firstNEmployees = new TextField();

		Label officeCodeEmployees = new Label();
		officeCodeEmployees.setText("OfficeCode");
		TextField officeCEmployees = new TextField();

		Label reportsToEmployees = new Label();
		reportsToEmployees.setText("reportsTo");
		TextField reportsEmployees = new TextField();

		Label jobTitleEmployees = new Label();
		jobTitleEmployees.setText("job Title");
		TextField jobTEmployees = new TextField();

		HBox employees1Insert = new HBox();
		employees1Insert.getChildren().addAll(employeeNumberEmployees, employeeNumEmployees);

		HBox employees2Insert = new HBox();
		employees2Insert.getChildren().addAll(lastNameEmployees, lastNEmployees);

		HBox employees3Insert = new HBox();
		employees3Insert.getChildren().addAll(firstNameEmployees, firstNEmployees);

		HBox employees4Insert = new HBox();
		employees4Insert.getChildren().addAll(reportsToEmployees, reportsEmployees);

		HBox employees5Insert = new HBox();
		employees5Insert.getChildren().addAll(jobTitleEmployees, jobTEmployees);

		HBox employees6Insert = new HBox();
		employees6Insert.getChildren().addAll(officeCodeEmployees, officeCEmployees);

		HBox employeesBoxInsert1 = new HBox();
		employeesBoxInsert1.getChildren().addAll(employees1Insert, employees2Insert, employees3Insert);
		employeesBoxInsert1.setSpacing(20);

		HBox employeesBoxInsert2 = new HBox();
		employeesBoxInsert2.getChildren().addAll(employees4Insert, employees5Insert, employees6Insert);
		employeesBoxInsert2.setSpacing(20);

		HBox employeesBoxInsert = new HBox();
		employeesBoxInsert.getChildren().addAll(employeesBoxInsert1, employeesBoxInsert2);
		employeesBoxInsert.setSpacing(20);

		///////////////////////////////// offices
		///////////////////////////////// Insert///////////////////////////////////

		Label officeCodeOffices = new Label();
		officeCodeOffices.setText("office Code");
		TextField officeCOffices = new TextField();

		Label officeCityOffices = new Label();
		officeCityOffices.setText("Office City");
		TextField cityOfficeOffices = new TextField();

		Label officeStateOffices = new Label();
		officeStateOffices.setText("office state");
		TextField officeSOffices = new TextField();

		Label officeCountryOffices = new Label();
		officeCountryOffices.setText("office Country");
		TextField countryOfficeOffices = new TextField();

		HBox offices1Insert = new HBox();
		offices1Insert.getChildren().addAll(officeCodeOffices, officeCOffices);

		HBox offices2Insert = new HBox();
		offices2Insert.getChildren().addAll(officeCityOffices, cityOfficeOffices);

		HBox offices3Insert = new HBox();
		offices3Insert.getChildren().addAll(officeStateOffices, officeSOffices);

		HBox offices4Insert = new HBox();
		offices4Insert.getChildren().addAll(officeCountryOffices, countryOfficeOffices);

		HBox officeBoxesInsert1 = new HBox();
		officeBoxesInsert1.getChildren().addAll(offices1Insert, offices2Insert);
		officeBoxesInsert1.setSpacing(80);

		HBox officeBoxesInsert2 = new HBox();
		officeBoxesInsert2.getChildren().addAll(offices3Insert, offices4Insert);
		officeBoxesInsert2.setSpacing(80);

		HBox officeBoxesInsert = new HBox();
		officeBoxesInsert.getChildren().addAll(officeBoxesInsert1, officeBoxesInsert2);
		officeBoxesInsert.setSpacing(80);

		//////////////////////////////// order details Insert///////////////////////

		Label orderNumberOrderDetails = new Label();
		orderNumberOrderDetails.setText("orderNumber");
		TextField orderNumOrderDetails = new TextField();

		Label productCodeOrderDetails = new Label();
		productCodeOrderDetails.setText("productCode");
		TextField productCOrderDetails = new TextField();

		Label quantityOrderDetails = new Label();
		quantityOrderDetails.setText("Quantity Ordered");
		TextField quantityOOrderDetails = new TextField();

		Label priceEachOrderDetails = new Label();
		priceEachOrderDetails.setText("Price");
		TextField eachPriceOrderDetails = new TextField();

		Label orderLineNumOrderDetails = new Label();
		orderLineNumOrderDetails.setText("Line Number");
		TextField lineNumOrderDetails = new TextField();

		HBox details1Insert = new HBox();
		details1Insert.getChildren().addAll(orderNumberOrderDetails, orderNumOrderDetails);

		HBox details2Insert = new HBox();
		details2Insert.getChildren().addAll(productCodeOrderDetails, productCOrderDetails);

		HBox details3Insert = new HBox();
		details3Insert.getChildren().addAll(quantityOrderDetails, quantityOOrderDetails);

		HBox details4Insert = new HBox();
		details4Insert.getChildren().addAll(priceEachOrderDetails, eachPriceOrderDetails);

		HBox details5Insert = new HBox();
		details5Insert.getChildren().addAll(orderLineNumOrderDetails, lineNumOrderDetails);

		HBox orderDetailsBoxesInsert1 = new HBox();
		orderDetailsBoxesInsert1.getChildren().addAll(details1Insert, details2Insert, details3Insert);
		orderDetailsBoxesInsert1.setSpacing(72);

		HBox orderDetailsBoxesInsert2 = new HBox();
		orderDetailsBoxesInsert2.getChildren().addAll(details4Insert, details5Insert);
		orderDetailsBoxesInsert2.setSpacing(72);

		HBox orderDetailsBoxesInsert = new HBox();
		orderDetailsBoxesInsert.getChildren().addAll(orderDetailsBoxesInsert1, orderDetailsBoxesInsert2);
		orderDetailsBoxesInsert.setSpacing(72);

		////////////////////////// orders Insert////////////////////////////

		Label orderNumber2Orders = new Label();
		orderNumber2Orders.setText("order number");
		TextField orderNum2Orders = new TextField();

		Label orderDateOrders = new Label();
		orderDateOrders.setText("order date");
		TextField orderDOrders = new TextField();

		Label customerNumber2Orders = new Label();
		customerNumber2Orders.setText("customer Number");
		TextField customerNum3Orders = new TextField();

		HBox orders1Insert = new HBox();
		orders1Insert.getChildren().addAll(orderNumber2Orders, orderNum2Orders);

		HBox orders2Insert = new HBox();
		orders2Insert.getChildren().addAll(orderDateOrders, orderDOrders);

		HBox orders3Insert = new HBox();
		orders3Insert.getChildren().addAll(customerNumber2Orders, customerNum3Orders);

		HBox orderBoxesInsert1 = new HBox();
		orderBoxesInsert1.getChildren().addAll(orders1Insert, orders2Insert);
		orderBoxesInsert1.setSpacing(72);

		HBox orderBoxesInsert2 = new HBox();
		orderBoxesInsert2.getChildren().addAll(orders3Insert);
		orderBoxesInsert2.setSpacing(72);

		HBox orderBoxesInsert = new HBox();
		orderBoxesInsert.getChildren().addAll(orderBoxesInsert1, orderBoxesInsert2);
		orderBoxesInsert.setSpacing(72);

		/////////////////////////////////////// ProductLines Insert///////////////

		Label productLineProductLines = new Label();
		productLineProductLines.setText("Product Line");
		TextField productLProductLines = new TextField();

		Label textDescriptionProductLines = new Label();
		textDescriptionProductLines.setText("text Description");
		TextField textDProductLines = new TextField();

		HBox productlines1Insert = new HBox();
		productlines1Insert.getChildren().addAll(productLineProductLines, productLProductLines);

		HBox productlines2Insert = new HBox();
		productlines2Insert.getChildren().addAll(textDescriptionProductLines, textDProductLines);

		HBox productlinesBoxesInsert = new HBox();
		productlinesBoxesInsert.getChildren().addAll(productlines1Insert, productlines2Insert);
		productlinesBoxesInsert.setSpacing(100);

		////////////////////////////////////// products insert//////////////////

		Label productCode2Products = new Label();
		productCode2Products.setText("product code");
		TextField productC2Products = new TextField();

		Label productNameProducts = new Label();
		productNameProducts.setText("product Name");
		TextField productNProducts = new TextField();

		Label productLine2Products = new Label();
		productLine2Products.setText("product Line");
		TextField productL2Products = new TextField();

		Label productDescriptionProducts = new Label();
		productDescriptionProducts.setText("Description");
		TextField productDProducts = new TextField();

		Label quantityPProducts = new Label();
		quantityPProducts.setText("quantity");
		TextField productQProducts = new TextField();

		Label buyPriceProducts = new Label();
		buyPriceProducts.setText("Price");
		TextField buyPProducts = new TextField();

		HBox products1Insert = new HBox();
		products1Insert.getChildren().addAll(productCode2Products, productC2Products);

		HBox products2Insert = new HBox();
		products2Insert.getChildren().addAll(productNameProducts, productNProducts);

		HBox products3Insert = new HBox();
		products3Insert.getChildren().addAll(productDescriptionProducts, productDProducts);

		HBox products4Insert = new HBox();
		products4Insert.getChildren().addAll(quantityPProducts, productQProducts);

		HBox products5Insert = new HBox();
		products5Insert.getChildren().addAll(productLine2Products, productL2Products);

		HBox products6Insert = new HBox();
		products6Insert.getChildren().addAll(buyPriceProducts, buyPProducts);

		HBox productBoxInsert1 = new HBox();
		productBoxInsert1.getChildren().addAll(products1Insert, products2Insert, products3Insert);
		productBoxInsert1.setSpacing(25);

		HBox productBoxInsert2 = new HBox();
		productBoxInsert2.getChildren().addAll(products4Insert, products5Insert, products6Insert);
		productBoxInsert2.setSpacing(25);

		HBox productBoxInsert = new HBox();
		productBoxInsert.getChildren().addAll(productBoxInsert1, productBoxInsert2);
		productBoxInsert.setSpacing(25);

		///////////////////////////////////////////////////////

		Text searchText = new Text();
		searchText.setText("1.Choose one of the tables to search from the search menuebar in the top left corner\n\n"
				+ "2.Choose one of the tables to add new information to from the insert button from the top left corner\n\n"
				+ "3.Click one of the buttons on the right side of the screen to view a table"
				+ "\n\n4.choose one of the info menuebard sections in the top left corner to get more information"
				+ "\n\n5.Choose the special operations section to execute additional operations");
		searchText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 17));
		searchText.setFill(Color.WHITE);
		searchText.setTextAlignment(TextAlignment.CENTER);
		fp.setCenter(searchText);
		//////////////////////Menus///////////////////////////

		// for search
		Menu tablesMenuSearch = new Menu("Search");

		// for insert
		Menu tablesMenuInsert = new Menu("Insert");

		// for queries
		Menu queriesMenu = new Menu("Special Operations");

		// search menuitems
		MenuItem custMenuItem = new MenuItem("Customers");
		MenuItem orderDetailsMenuItem = new MenuItem("Order Details");
		MenuItem productLinesMenuItem = new MenuItem("Product Lines");
		MenuItem ordersMenuItem = new MenuItem("Orders");
		MenuItem officesMenuItem = new MenuItem("Offices");
		MenuItem productsMenuItem = new MenuItem("Products");
		MenuItem paymentsMenuItem = new MenuItem("Payments");
		MenuItem employeesMenuItem = new MenuItem("Employees");

		// insert menuitems
		MenuItem custMenuItemInsert = new MenuItem("Customers");
		MenuItem orderDetailsMenuItemInsert = new MenuItem("Order Details");
		MenuItem productLinesMenuItemInsert = new MenuItem("Product Lines");
		MenuItem ordersMenuItemInsert = new MenuItem("Orders");
		MenuItem officesMenuItemInsert = new MenuItem("Offices");
		MenuItem productsMenuItemInsert = new MenuItem("Products");
		MenuItem paymentsMenuItemInsert = new MenuItem("Payments");
		MenuItem employeesMenuItemInsert = new MenuItem("Employees");

		// operation menuitems

		////////////////////// customers order from a specific country ///////////////////
		MenuItem query1 = new MenuItem("Product from a specific country");
		Label countryQuery = new Label();
		countryQuery.setText("Country");
		TextField countryQueryField = new TextField();

		HBox query1Box = new HBox();
		query1Box.getChildren().addAll(countryQuery, countryQueryField);
		query1Box.setSpacing(20);

		Label cityQuery = new Label();
		cityQuery.setText("City");
		TextField cityQueryField = new TextField();

		HBox query2Box = new HBox();
		query2Box.getChildren().addAll(cityQuery, cityQueryField);
		query2Box.setSpacing(20);

		HBox query1BoxAll = new HBox();
		query1BoxAll.getChildren().addAll(query1Box, query2Box);
		query1BoxAll.setSpacing(20);

		query1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				operationSelection = "query1";
				fp.setTop(query1BoxAll);
				query1BoxAll.setAlignment(Pos.CENTER);
				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		/////////////////////////////// orderdetails with a specific price /////////////////
		MenuItem query2 = new MenuItem("Highest Prices");

		Label buypriceQuery = new Label();
		buypriceQuery.setText("Price");
		TextField priceQuery = new TextField();

		HBox query2BoxAll = new HBox();
		query2BoxAll.getChildren().addAll(buypriceQuery, priceQuery);
		query2BoxAll.setSpacing(20);

		query2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				operationSelection = "query2";
				fp.setTop(query2BoxAll);
				query2BoxAll.setAlignment(Pos.CENTER);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		////////////////////////// customers credit with a given range ////////////////

		MenuItem query3 = new MenuItem("specifying Range");

		Label range1 = new Label();
		range1.setText("Range1 CreditL");
		TextField range1Field = new TextField();

		Label range2 = new Label();
		range2.setText("Range2 CreditL");
		TextField range2Field = new TextField();

		HBox query3Box1 = new HBox();
		query3Box1.getChildren().addAll(range1, range1Field);
		query3Box1.setSpacing(20);

		HBox query3Box2 = new HBox();
		query3Box2.getChildren().addAll(range2, range2Field);
		query3Box2.setSpacing(20);

		HBox query3All = new HBox();
		query3All.getChildren().addAll(query3Box1, query3Box2);
		query3All.setSpacing(20);

		query3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				operationSelection = "query3";
				fp.setTop(query3All);
				query3All.setAlignment(Pos.CENTER);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		//////////////////// customers and their products //////////////////////

		MenuItem query4 = new MenuItem("Customers' order List");

		query4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				tableView(fp,
						"SELECT customers.customerName, customers.customerNumber, products.productName, products.productCode FROM products, orderdetails, orders, customers WHERE products.productCode = orderdetails.productCode AND orderdetails.orderNumber = orders.orderNumber AND orders.customerNumber = customers.customerNumber");

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		//////////////////// searching for products using description//////////////////////

		MenuItem query5 = new MenuItem("Description Search");

		Label keyWord = new Label();
		keyWord.setText("Product Description keyword/s");
		TextField key = new TextField();

		HBox query5All = new HBox();
		query5All.getChildren().addAll(keyWord, key);
		query5All.setSpacing(20);

		query5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				operationSelection = "query5";
				fp.setTop(query5All);
				query5All.setAlignment(Pos.CENTER);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		/////////////////////// employees and orders they have facilitated ///////////////////

		MenuItem query6 = new MenuItem("Employees' order facilitation");

		query6.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				tableView(fp,
						"Select employees.firstName, employees.lastName, employees.employeeNumber, orders.orderNumber, orders.orderDate FROM employees, customers, orders WHERE employees.employeeNumber = customers.salesRepEmployeeNumber AND customers.customerNumber = orders.customerNumber");

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		///////////////// employees and total prices of products  they sold /////////////////////////

		MenuItem query7 = new MenuItem("Employee selling prices");

		query7.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				tableView(fp,
						"Select employees.firstName, products.buyPrice FROM employees, customers, orders, orderdetails, products WHERE employees.employeeNumber = customers.salesRepEmployeeNumber AND customers.customerNumber = orders.customerNumber AND orders.orderNumber = orderdetails.orderNumber AND orderdetails.productCode = products.productCode GROUP BY employeeNumber");

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		//////////////////// queries of offices names and money they earned //////////////////////

		MenuItem query8 = new MenuItem("Offices Income");

		query8.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				tableView(fp,
						"SELECT offices.officeCode, payments.amount FROM offices, employees, customers, payments WHERE offices.officeCode = employees.officeCode AND employees.employeeNumber = customers.salesRepEmployeeNumber AND customers.customerNumber = payments.customerNumber GROUP BY offices.officeCode");

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		//////////////////////// employees who don't sell products //////////////////

		MenuItem query9 = new MenuItem("None selling employees");

		query9.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				tableView(fp,
						"SELECT employeeNumber, CONCAT(employees.firstName, employees.lastName) AS Full_Name FROM employees WHERE employees.employeeNumber NOT IN (SELECT DISTINCT customers.salesRepEmployeeNumber FROM customers WHERE customers.salesRepEmployeeNumber IS NOT NULL) ORDER BY employeeNumber ASC");

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		///////////////////////// employees who sold at least one product //////////////

		MenuItem query10 = new MenuItem("One product Selling employees");

		query10.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				tableView(fp,
						"SELECT employeeNumber, CONCAT(employees.firstName,  employees.lastName) AS FULL_NAME , COUNT("
								+ "products.productCode) AS Selling_Quantity FROM employees , customers, orders,orderdetails , products WHERE employees.employeeNumber = "
								+ "customers.salesRepEmployeeNumber AND customers.customerNumber = orders.customerNumber AND orders.orderNumber = orderdetails.orderNumber AND orderdetails.productCode="
								+ " products.productCode GROUP BY employeeNumber HAVING COUNT(products.productCode) >1;");

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

			}
		});

		////////////////////////////////////////////////
		queriesMenu.getItems().addAll(query1, query2, query3, query4, query5, query6, query7, query8, query9, query10);

		tablesMenuSearch.getItems().addAll(custMenuItem, orderDetailsMenuItem, productLinesMenuItem, ordersMenuItem,
				officesMenuItem, productsMenuItem, paymentsMenuItem, employeesMenuItem);

		tablesMenuInsert.getItems().addAll(custMenuItemInsert, orderDetailsMenuItemInsert, productLinesMenuItemInsert,
				ordersMenuItemInsert, officesMenuItemInsert, productsMenuItemInsert, paymentsMenuItemInsert,
				employeesMenuItemInsert);

		MenuBar tableBar = new MenuBar();
		tableBar.getMenus().addAll(tablesMenuSearch, abtM, tablesMenuInsert, queriesMenu);
		mainBar.getChildren().add(tableBar);

		///////////////// menu items for search///////////////////

		custMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "customers";

				customersBoxes.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(customersBoxes);

			}
		});

		orderDetailsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "orderDetails";

				orderDetailsBoxes.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(orderDetailsBoxes);

			}
		});

		productLinesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "productLines";

				productlinesBoxes.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(productlinesBoxes);

			}
		});

		employeesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "employees";

				employeesBox.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(employeesBox);

			}
		});

		officesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "offices";

				officeBoxes.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(officeBoxes);

			}
		});

		productsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "products";

				productBox.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(productBox);

			}
		});

		paymentsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "payments";

				paymentBoxes.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(paymentBoxes);

			}
		});

		ordersMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "orders";

				orderBoxes.setVisible(true);

				customersBoxesInsert.setVisible(false);
				orderDetailsBoxesInsert.setVisible(false);
				productlinesBoxesInsert.setVisible(false);
				employeesBoxInsert.setVisible(false);
				officeBoxesInsert.setVisible(false);
				productBoxInsert.setVisible(false);
				paymentBoxesInsert.setVisible(false);
				orderBoxesInsert.setVisible(false);

				fp.setTop(orderBoxes);

			}
		});

		///////////////////// Menu items
		///////////////////// insert////////////////////////////////////////////////////////////////////////////////////////////////////////////

		paymentsMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				currentSelection = "payments";

				paymentBoxesInsert.setVisible(true);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

				fp.setTop(paymentBoxesInsert);
				paymentBoxesInsert.setAlignment(Pos.CENTER);
			}
		});

		custMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "customers";

				customersBoxesInsert.setVisible(true);
				tableView(fp, "Select employeeNumber from employees");
				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

				fp.setTop(customersBoxesInsert);
				customersBoxesInsert.setAlignment(Pos.CENTER);
			}
		});

		employeesMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "employees";

				employeesBoxInsert.setVisible(true);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

				fp.setTop(employeesBoxInsert);
				employeesBoxInsert.setAlignment(Pos.CENTER);
			}

		});

		officesMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "offices";

				officeBoxesInsert.setVisible(true);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

				fp.setTop(officeBoxesInsert);
				officeBoxesInsert.setAlignment(Pos.CENTER);
			}

		});

		orderDetailsMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "orderDetails";

				orderDetailsBoxesInsert.setVisible(true);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

				fp.setTop(orderDetailsBoxesInsert);
				orderDetailsBoxesInsert.setAlignment(Pos.CENTER);
			}

		});

		ordersMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "orders";

				orderBoxesInsert.setVisible(true);

				tableView(fp, "Select customerNumber from customers");

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

				fp.setTop(orderBoxesInsert);
				orderBoxesInsert.setAlignment(Pos.CENTER);
			}

		});

		productLinesMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "productLines";

				productlinesBoxesInsert.setVisible(true);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);

				fp.setTop(productlinesBoxesInsert);
				productlinesBoxesInsert.setAlignment(Pos.CENTER);
			}

		});

		productsMenuItemInsert.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				currentSelection = "products";

				productBoxInsert.setVisible(true);

				customersBoxes.setVisible(false);
				orderDetailsBoxes.setVisible(false);
				productlinesBoxes.setVisible(false);
				employeesBox.setVisible(false);
				officeBoxes.setVisible(false);
				productBox.setVisible(false);
				paymentBoxes.setVisible(false);
				orderBoxes.setVisible(false);
				tableView(fp, "Select productLine from productlines");
				fp.setTop(productBoxInsert);
				productBoxInsert.setAlignment(Pos.CENTER);
			}

			// productBoxInsert
		});

		////////////////////////////////////////////////////////////////

		Button search = new Button();
		search.setText("Search");
		search.setMinSize(100, 80);
		search.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (currentSelection == "customers") {

					String sql = "";

					if (!customerNum.getText().isEmpty() || !customerName.getText().isEmpty()
							|| !cityN.getText().isEmpty() || !countryN.getText().isEmpty()
							|| !salesRep.getText().isEmpty() || !creditL.getText().isEmpty()) {

						sql = "select * from customers where";

						if (!customerNum.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(customerNum.getText()) == true) {

									b = false;

									sql += " customerNumber =" + customerNum.getText() + " AND";

								} else if (isNumber(customerNum.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // customerNumber if

						if (!customerName.getText().isEmpty()) {

							sql += " customerName like '%" + customerName.getText() + "%' AND";
						} // customerName if

						if (!cityN.getText().isEmpty()) {

							sql += " city like '%" + cityN.getText() + "%' AND";
						} // customerCity if

						if (!countryN.getText().isEmpty()) {

							sql += " country like '%" + countryN.getText() + "%' AND";
						} // customer country if

						if (!salesRep.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(salesRep.getText()) == true) {

									b = false;

									sql += " salesRepEmployeeNumber =" + salesRep.getText() + " AND";

								} else if (isNumber(salesRep.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // salesRep if

						if (!creditL.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isDouble(creditL.getText()) == true) {

									b = false;

									sql += " creditLimit =" + creditL.getText() + " AND";

								} else if (isDouble(creditL.getText()) == false) {

									Text validation = new Text();
									validation.setText("You have mistakenly entered a string in a number textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // credit if

					} // if statement for all customer attributes

					if (customerNum.getText().isEmpty() && customerName.getText().isEmpty() && cityN.getText().isEmpty()
							&& countryN.getText().isEmpty() && salesRep.getText().isEmpty()
							&& creditL.getText().isEmpty()) {

						Text emptyCustAtt = new Text();
						emptyCustAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyCustAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyCustAtt.setFill(Color.WHITE);
						emptyCustAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyCustAtt);

					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				} // if for current selection customer.

				if (currentSelection == "orderDetails") {

					String sql = "";

					if (!orderNum.getText().isEmpty() || !productC.getText().isEmpty() || !quantityO.getText().isEmpty()
							|| !eachPrice.getText().isEmpty() || !lineNum.getText().isEmpty()) {

						sql = "select * from orderDetails where";

						if (!orderNum.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(orderNum.getText()) == true) {

									b = false;

									sql += " orderNumber =" + orderNum.getText() + " AND";

								} else if (isNumber(orderNum.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // ordernum if

						if (!productC.getText().isEmpty()) {

							sql += " productCode like '%" + productC.getText() + "%' AND";
						} // productC if

						if (!quantityO.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(quantityO.getText()) == true) {

									b = false;

									sql += " quantityOrdered =" + quantityO.getText() + " AND";

								} else if (isNumber(quantityO.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // quantityOrdered if

						if (!eachPrice.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isDouble(eachPrice.getText()) == true) {

									b = false;

									sql += " priceEach =" + eachPrice.getText() + " AND";

								} else if (isDouble(eachPrice.getText()) == false) {

									Text validation = new Text();
									validation.setText("You have mistakenly entered a string in a number textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // eachPrice if

						if (!lineNum.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(lineNum.getText()) == true) {

									b = false;

									sql += " orderLineNumber =" + lineNum.getText() + " AND";

								} else if (isNumber(lineNum.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // lineNum if

					} // if statement for all orderDetails attributes

					if (customerNum.getText().isEmpty() && customerName.getText().isEmpty() && cityN.getText().isEmpty()
							&& countryN.getText().isEmpty() && salesRep.getText().isEmpty()
							&& creditL.getText().isEmpty()) {

						Text emptyCustAtt = new Text();
						emptyCustAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyCustAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyCustAtt.setFill(Color.WHITE);
						emptyCustAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyCustAtt);
					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} // if for current selection orderdetails.

				if (currentSelection == "productLines") {

					String sql = "";

					if (!productL.getText().isEmpty() || !textD.getText().isEmpty()) {

						sql = "select * from productLines where";

						if (!productLine.getText().isEmpty()) {

							sql += " productLine like '%" + productL.getText() + "%' AND";
						} // productLine if

						if (!textD.getText().isEmpty()) {

							sql += " textDescription like '%" + textD.getText() + "%' AND";
						} // textDecription if

					} // if statement for all productLines attributes

					if (textD.getText().isEmpty() && productL.getText().isEmpty()) {

						Text emptyCustAtt = new Text();
						emptyCustAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyCustAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyCustAtt.setFill(Color.WHITE);
						emptyCustAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyCustAtt);
					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				} // if for current selection productLines

				if (currentSelection == "employees") {

					String sql = "";

					if (!employeeNum.getText().isEmpty() || !lastN.getText().isEmpty() || !firstN.getText().isEmpty()
							|| !reports.getText().isEmpty() || !jobT.getText().isEmpty()) {

						sql = "select * from employees where";

						if (!employeeNum.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(employeeNum.getText()) == true) {

									b = false;

									sql += " employeeNumber =" + employeeNum.getText() + " AND";

								} else if (isNumber(employeeNum.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // employeeNumber if

						if (!lastN.getText().isEmpty()) {

							sql += " lastName like '%" + lastN.getText() + "%' AND";
						} // lastName if

						if (!firstN.getText().isEmpty()) {

							sql += " firstName like '%" + firstN.getText() + "%' AND";
						} // firstName if

						if (!reports.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(reports.getText()) == true) {

									b = false;

									sql += " reportsTo =" + reports.getText() + " AND";

								} else if (isNumber(reports.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // reportsTo if

						if (!jobT.getText().isEmpty()) {

							sql += " jobTitle like '%" + jobT.getText() + "%' AND";
						} // jobTitle if

					} // if statement for all orderDetails attributes

					if (employeeNum.getText().isEmpty() && lastN.getText().isEmpty() && firstN.getText().isEmpty()
							&& reports.getText().isEmpty() && jobT.getText().isEmpty()) {

						Text emptyCustAtt = new Text();
						emptyCustAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyCustAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyCustAtt.setFill(Color.WHITE);
						emptyCustAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyCustAtt);
					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} // if for current selection employees.

				if (currentSelection == "offices") {

					String sql = "";

					if (!officeC.getText().isEmpty() || !countryOffice.getText().isEmpty()
							|| !cityOffice.getText().isEmpty() || !officeS.getText().isEmpty()) {

						sql = "select * from offices where";

						if (!officeC.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(officeC.getText()) == true) {

									b = false;

									sql += " officeCode =" + officeC.getText() + " AND";

								} else if (isNumber(officeC.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // officeC if

						if (!countryOffice.getText().isEmpty()) {

							sql += " country like '%" + countryOffice.getText() + "%' AND";
						} // country if

						if (!officeS.getText().isEmpty()) {

							sql += " state like '%" + officeS.getText() + "%' AND";
						} // state if

						if (!cityOffice.getText().isEmpty()) {

							sql += " city like '%" + officeS.getText() + "%' AND";
						} // city if

					} // if statement for all offices attributes

					if (officeC.getText().isEmpty() && cityOffice.getText().isEmpty()
							&& countryOffice.getText().isEmpty() && officeS.getText().isEmpty()) {

						Text emptyCustAtt = new Text();
						emptyCustAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyCustAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyCustAtt.setFill(Color.WHITE);
						emptyCustAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyCustAtt);
					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
//						Statement stmt = con.createStatement();
//						ResultSet rs = stmt.executeQuery(sql);
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} // if for current selection offices.

				if (currentSelection == "products") {

					String sql = "";

					if (!productC2.getText().isEmpty() || !productN.getText().isEmpty()
							|| !productL2.getText().isEmpty() || !productD.getText().isEmpty()
							|| !productQ.getText().isEmpty() || !buyP.getText().isEmpty()) {

						sql = "select * from products where";

						if (!productC.getText().isEmpty()) {

							sql += " productCode like '%" + productN.getText() + "%' AND";
						} // productCode if

						if (!productN.getText().isEmpty()) {

							sql += " productName like '%" + productN.getText() + "%' AND";
						} // productName if

						if (!productL2.getText().isEmpty()) {

							sql += " productLine like '%" + cityN.getText() + "%' AND";
						} // productLine if

						if (!productD.getText().isEmpty()) {

							sql += " productDescription like '%" + countryN.getText() + "%' AND";
						} // productDescription if

						if (!productQ.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(productQ.getText()) == true) {

									b = false;

									sql += " quantityInStock =" + productQ.getText() + " AND";

								} else if (isNumber(productQ.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // quantity if

						if (!buyP.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isDouble(buyP.getText()) == true) {

									b = false;

									sql += " buyPrice =" + buyP.getText() + " AND";

								} else if (isDouble(buyP.getText()) == false) {

									Text validation = new Text();
									validation.setText("You have mistakenly entered a string in a number textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // buyPrice if

					} // if statement for all products attributes

					if (productC2.getText().isEmpty() && productN.getText().isEmpty() && productL2.getText().isEmpty()
							&& productD.getText().isEmpty() && productQ.getText().isEmpty()
							&& buyP.getText().isEmpty()) {

						Text emptyCustAtt = new Text();
						emptyCustAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyCustAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyCustAtt.setFill(Color.WHITE);
						emptyCustAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyCustAtt);
					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

				} // if for current selection products.

				if (currentSelection == "payments") {

					String sql = "";

					if (!customerNum2.getText().isEmpty() || !paymentD.getText().isEmpty()
							|| !amount2.getText().isEmpty()) {

						sql = "select * from payments where";

						if (!customerNum2.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(customerNum2.getText()) == true) {

									b = false;

									sql += " customerNumber =" + customerNum2.getText() + " AND";

								} else if (isNumber(customerNum2.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // customerNumber in payments table if

						if (!paymentD.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isDate(paymentD.getText()) == true) {

									b = false;

									sql += " paymentDate like '%" + paymentD.getText() + "%' AND";

								} else if (isDate(paymentD.getText()) == false) {

									Text validation = new Text();
									validation.setText("Wrong date format");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}

						} // PaymentDate in payments table if

						if (!amount2.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isDouble(amount2.getText()) == true) {

									b = false;

									sql += " amount =" + amount2.getText() + " AND";

								} else if (isDouble(amount2.getText()) == false) {

									Text validation = new Text();
									validation.setText("You have mistakenly entered a string in a number textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // amount in payments table if

					} // if statement for all payments attributes

					if (customerNum2.getText().isEmpty() && paymentD.getText().isEmpty()
							&& amount2.getText().isEmpty()) {

						Text emptyPaymentsAtt = new Text();
						emptyPaymentsAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyPaymentsAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyPaymentsAtt.setFill(Color.WHITE);
						emptyPaymentsAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyPaymentsAtt);

					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} // if for current selection payments.

				if (currentSelection == "orders") {

					String sql = "";

					if (!orderNum2.getText().isEmpty() || !orderD.getText().isEmpty()
							|| !customerNum3.getText().isEmpty()) {

						sql = "select * from orders where";

						if (!orderNum2.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(orderNum2.getText()) == true) {

									b = false;

									sql += " orderNumber =" + orderNum2.getText() + " AND";

								} else if (isNumber(orderNum2.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // order Number in orders table if

						if (!orderD.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isDate(orderD.getText()) == true) {

									b = false;

									sql += " orderDate like '%" + orderD.getText() + "%' AND";

								} else if (isDate(orderD.getText()) == false) {

									Text validation = new Text();
									validation.setText("Wrong date format");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}

						} // orderDate in orders table if

						if (!customerNum3.getText().isEmpty()) {

							boolean b = true;

							while (b) {

								if (isNumber(customerNum3.getText()) == true) {

									b = false;

									sql += " customerNumber =" + customerNum3.getText() + " AND";

								} else if (isNumber(customerNum3.getText()) == false) {

									Text validation = new Text();
									validation.setText(
											"You have mistakenly entered a string or a float value in an Integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								}
							}
						} // customerNumber in payments table if

					} // if statement for all orders attributes

					if (customerNum3.getText().isEmpty() && orderD.getText().isEmpty()
							&& orderNum2.getText().isEmpty()) {

						Text emptyPaymentsAtt = new Text();
						emptyPaymentsAtt.setText("THE TEXT FIELDS ARE EMPTY");
						emptyPaymentsAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyPaymentsAtt.setFill(Color.WHITE);
						emptyPaymentsAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyPaymentsAtt);

					}

					try {

						if (sql.endsWith("AND")) {

							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						tableView(fp, sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} // if for current selection orders.
			}

		}); // Search set on action.

		//////////////////////////
		Button insert = new Button();
		insert.setText("Insert");
		insert.setMinSize(100, 80);
		insert.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if (currentSelection == "payments") {
					String sql = "";

					if (!customerNum2Payments.getText().isEmpty() && !paymentDatePayments.getText().isEmpty()
							&& !amount2Payments.getText().isEmpty() && !checkNumPayments.getText().isEmpty()) {

						sql = "INSERT INTO payments VALUES(";

						if (isNumber(customerNum2Payments.getText()) == true) {

							sql += "'" + customerNum2Payments.getText() + "', ";

						} else if (isNumber(customerNum2Payments.getText()) == false
								&& !isDouble(customerNum2Payments.getText())) {
							System.out.println("validation error loop");
							Text validation = new Text();
							validation.setText("You cannot insert a string or a double");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						sql += "'" + checkNumPayments.getText() + "', ";

						if (isDate(paymentDPayments.getText()) == true) {

//							b = false;
							System.out.println("===" + paymentDPayments.getText());
							sql += "'" + paymentDPayments.getText() + "', ";

						} else if (isDate(paymentD.getText()) == false) {
							System.out.println("validation error loop [paymentDate]");
							Text validation = new Text();
							validation.setText("Wrong date format");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							;
						}

						if (isDouble(amount2Payments.getText()) == true) {

							sql += "'" + amount2Payments.getText() + "')";
							System.out.println(sql);

						} else if (isDouble(amount2Payments.getText()) == false) {
							System.out.println("validation error loop [paymentDate]");

							Text validation = new Text();
							validation.setText("You have mistakenly entered a string in a number textfield");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

					} // if statement for all payments insert attributes

					else if (customerNum2Payments.getText().isEmpty() || paymentDPayments.getText().isEmpty()
							|| amount2Payments.getText().isEmpty()) {

						Text emptyPaymentsAtt = new Text();
						emptyPaymentsAtt.setText("ALL FIELDS ARE REQUIRED!");
						emptyPaymentsAtt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						emptyPaymentsAtt.setFill(Color.WHITE);
						emptyPaymentsAtt.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(emptyPaymentsAtt);

					} else {

						Text validation = new Text();
						validation.setText("YOU HAVEN'T ACTIVATED INSERTION YET!");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
					}

					try {

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						Statement stmt = con.createStatement();

						stmt.executeUpdate(sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					tableView(fp, "SELECT * FROM payments");
				} // if insert for payments.

				if (currentSelection == "productLines") {

					String sql = "";

					if (!productLProductLines.getText().isEmpty() || !textDProductLines.getText().isEmpty()) {

						sql = "INSERT INTO productlines(productLine, ";
						String[] array = new String[2];
						if (!productLProductLines.getText().isEmpty()) {

							array[0] = productLProductLines.getText();

						} else if (productLProductLines.getText().isEmpty()) {
							System.out.println("validation error loop");
							Text validation = new Text();
							validation.setText("YOU MUST INSERT A PRODUCT LINE");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						if (!textDProductLines.getText().isEmpty()) {

							sql += "textDescription) VALUES(";
							array[1] = textDProductLines.getText();
						}

						for (int i = 0; i < array.length; ++i) {

							if (array[i] != null) {

								sql += "'" + array[i] + "',";
							}
						}

						if (sql.endsWith(",")) {

							String sqlSub = sql.substring(0, sql.length() - 1);
							sql = sqlSub + ")";
						}

					} // if statement for all produtlines insert attributes

					else {

						Text validation = new Text();
						validation.setText("YOU HAVEN'T ACTIVATED INSERTION YET!");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
					}

					try {

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						Statement stmt = con.createStatement();

						stmt.executeUpdate(sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					tableView(fp, "SELECT * FROM productlines");
				} // if insert for productlines.

				if (currentSelection == "offices") {

					String sql = "";
					if (!officeCOffices.getText().isEmpty() || !cityOfficeOffices.getText().isEmpty()
							|| !countryOfficeOffices.getText().isEmpty() || !officeSOffices.getText().isEmpty()) {

						sql = "INSERT INTO offices(";
						String[] array = new String[4];
						if (!officeCOffices.getText().isEmpty() && !cityOfficeOffices.getText().isEmpty()
								&& !countryOfficeOffices.getText().isEmpty()) {

							if (isNumber(officeCOffices.getText())) {

								sql += "officeCode, city, ";

								array[0] = officeCOffices.getText();
								array[1] = cityOfficeOffices.getText();
								array[3] = countryOfficeOffices.getText();

							} else if (!isNumber(officeCOffices.getText())) {
								Text validation = new Text();
								validation.setText("YOU MUST ENTER A NUMBER ID");
								validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
								validation.setFill(Color.WHITE);
								validation.setTextAlignment(TextAlignment.CENTER);
								fp.setCenter(validation);
								return;
							}

						} else if (officeCOffices.getText().isEmpty() || cityOfficeOffices.getText().isEmpty()
								|| countryOfficeOffices.getText().isEmpty()) {
							Text validation = new Text();
							validation.setText("CITY, COUNTRY AND CODE ARE ESSENTIAL!");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						if (!officeSOffices.getText().isEmpty()) {

							sql += "state, ";
							array[2] = officeSOffices.getText();
						}

						sql += "country) VALUES(";

						for (int i = 0; i < array.length; ++i) {

							if (array[i] != null) {

								sql += "'" + array[i] + "',";
							}
						}

						if (sql.endsWith(",")) {

							String sqlSub = sql.substring(0, sql.length() - 1);
							sql = sqlSub + ")";
						}

					} // if statement for all offices insert attributes

					else {

						Text validation = new Text();
						validation.setText("YOU HAVEN'T ACTIVATED INSERTION YET!");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
					}

					try {

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						Statement stmt = con.createStatement();

						stmt.executeUpdate(sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					tableView(fp, "SELECT * FROM offices");
				} // if insert for offices

				if (currentSelection == "orders") {

					String sql = "";

					if (!orderNum2Orders.getText().isEmpty() && !orderDOrders.getText().isEmpty()
							&& !customerNum3Orders.getText().isEmpty()) {

						sql = "INSERT INTO orders VALUES(";

						if (isNumber(orderNum2Orders.getText())) {

							sql += orderNum2Orders.getText() + ", ";

						} else if (!isNumber(orderNum2Orders.getText())) {

							Text validation = new Text();
							validation.setText(
									"you mistakenly entered a string or a float value in an integer textfield");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						if (isDate(orderDOrders.getText())) {

							sql += "'" + orderDOrders.getText() + "', ";

						} else if (!isDate(orderDOrders.getText())) {

							Text validation = new Text();
							validation.setText("WRONG DATE FORMAT");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						if (isNumber(customerNum3Orders.getText())) {

							sql += customerNum3Orders.getText() + ")";

						} else if (!isNumber(customerNum3Orders.getText())) {

							Text validation = new Text();
							validation.setText("YOU HAVE ENTERED A FLOAT OR STRING VALUE IN AN INTEGER TEXTFIELD!");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

					} // if statement for all orders insert attributes

					else if (orderNum2Orders.getText().isEmpty() || orderDOrders.getText().isEmpty()
							|| customerNum3Orders.getText().isEmpty()) {

						Text validation = new Text();
						validation.setText("YOU NEED TO FILL ALL TEXTFIELDS!");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
						return;
					}

					else {

						Text validation = new Text();
						validation.setText("YOU HAVEN'T ACTIVATED INSERTION YET!");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
					}

					try {

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						Statement stmt = con.createStatement();

						stmt.executeUpdate(sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					tableView(fp, "SELECT * FROM orders");

				} // if insert for orders

				if (currentSelection == "customers") {

					String sql = "";

					if (!customerNumCustomers.getText().isEmpty() || !customerNameCustomers.getText().isEmpty()
							|| !cityNCustomers.getText().isEmpty() || !countryNCustomers.getText().isEmpty()
							|| !salesRepCustomers.getText().isEmpty() || !creditLCustomers.getText().isEmpty()) {

						String[] array = new String[6];

						if (!customerNumCustomers.getText().isEmpty() && !customerNameCustomers.getText().isEmpty()
								&& !cityNCustomers.getText().isEmpty() && !countryNCustomers.getText().isEmpty()) {

							sql = "INSERT INTO customers(customerNumber, customerName, city, country ";

							if (isNumber(customerNumCustomers.getText())) {

								array[0] = customerNumCustomers.getText();

							} else if (!isNumber(customerNumCustomers.getText())) {

								Text validation = new Text();
								validation.setText(
										"you mistakenly entered a string or a float value in an integer textfield");
								validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
								validation.setFill(Color.WHITE);
								validation.setTextAlignment(TextAlignment.CENTER);
								fp.setCenter(validation);
								return;
							}

							array[1] = customerNameCustomers.getText();
							array[2] = cityNCustomers.getText();
							array[3] = countryNCustomers.getText();
						} else if (customerNumCustomers.getText().isEmpty()
								|| !customerNameCustomers.getText().isEmpty() || cityNCustomers.getText().isEmpty()
								|| countryNCustomers.getText().isEmpty()) {

							Text validation = new Text();
							validation.setText("SOME ESSENTIAL INFORMATION ARE UNFILLED,\n"
									+ "ONLY CREDITL AND SALESREP ARE OPTIONAL!");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}
						if (!salesRepCustomers.getText().isEmpty() || !creditLCustomers.getText().isEmpty()) {

							if (!salesRepCustomers.getText().isEmpty()) {

								if (isNumber(salesRepCustomers.getText())) {

									sql += ", salesRepEmployeeNumber";
									array[4] = salesRepCustomers.getText();

								} else if (!isNumber(salesRepCustomers.getText())) {

									Text validation = new Text();
									validation.setText(
											"you mistakenly entered a string or a float value in an integer textfield");
									validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
									validation.setFill(Color.WHITE);
									validation.setTextAlignment(TextAlignment.CENTER);
									fp.setCenter(validation);
									return;
								} else if (salesRepCustomers.getText().isEmpty()) {
									return;
								}
							}

							if (isDouble(creditLCustomers.getText())) {

								sql += ", creditLimit";

								array[5] = creditLCustomers.getText();

							} else if (!isDouble(creditLCustomers.getText())) {

								Text validation = new Text();
								validation.setText("You have mistakenly entered a string in a creditLimit textfield!");
								validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
								validation.setFill(Color.WHITE);
								validation.setTextAlignment(TextAlignment.CENTER);
								fp.setCenter(validation);
								return;
							} else if (creditLCustomers.getText().isEmpty()) {

								return;
							}
						}

						sql += ") VALUES(";

						for (int i = 0; i < array.length; ++i) {

							if (array[i] != null) {

								sql += "'" + array[i] + "',";
							}
						}

					} // if statement for all customers insert attributes

					else {

						Text validation = new Text();
						validation.setText("YOU HAVEN'T ACTIVATED INSERTION YET!");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
					}

					try {

						if (sql.endsWith(",")) {

							String subSql = sql.substring(0, sql.length() - 1);
							sql = subSql;
							sql += ");";

						}

						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						Statement stmt = con.createStatement();

						stmt.executeUpdate(sql);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					tableView(fp, "SELECT * FROM customers");

				} // if customers insert

				if (currentSelection == "products") {

					if (productC2Products.getText().isEmpty() || productNProducts.getText().isEmpty()
							|| productL2Products.getText().isEmpty() || productDProducts.getText().isEmpty()
							|| productQProducts.getText().isEmpty() || buyPProducts.getText().isEmpty()) {

						Text validation = new Text();
						validation.setText("MAKE SURE TO FILL ALL FIELDS");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
					} else {

						String sql = "INSERT INTO products VALUES(";

						sql += "'" + productC2Products.getText() + "', '" + productNProducts.getText() + "', '"
								+ productL2Products.getText() + "', '" + productDProducts.getText() + "', ";

						if (isNumber(productQProducts.getText()) == true) {

							sql += productQProducts.getText() + ", ";

						} else if (isNumber(productQProducts.getText()) == false) {

							Text validation = new Text();
							validation.setText(
									"You have mistakenly entered a string or a float value in quantity textfield");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						if (isDouble(buyPProducts.getText()) == true) {

							sql += buyPProducts.getText() + ")";

						} else if (isDouble(buyPProducts.getText()) == false) {

							Text validation = new Text();
							validation.setText("Please enter a double value in Price textfield");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						try {

							System.out.println(sql);

							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels",
									"root", "");
							Statement stmt = con.createStatement();

							stmt.executeUpdate(sql);
							con.close();

						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

						tableView(fp, "SELECT * FROM products");

					}

				} // products insert

				if (currentSelection == "employees") {

					if (employeeNumEmployees.getText().isEmpty() || lastNEmployees.getText().isEmpty()
							|| firstNEmployees.getText().isEmpty() || jobTEmployees.getText().isEmpty()
							|| officeCEmployees.getText().isEmpty()) {

						Text validation = new Text();
						validation.setText(
								"THE ONLY OPTIONAL FIELD IS REPORTS TO\n PLEASE MAKE SURE YOU FILLED ALL NECCESSARY FIELDS");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
					} else if ((!employeeNumEmployees.getText().isEmpty() && !lastNEmployees.getText().isEmpty()
							&& !firstNEmployees.getText().isEmpty() && !jobTEmployees.getText().isEmpty()
							&& !officeCEmployees.getText().isEmpty()) || !reportsEmployees.getText().isEmpty()) {

						String sql = "INSERT INTO employees(";
						String[] array = new String[6];

						if (isNumber(employeeNumEmployees.getText()) == true) {

							sql += "employeeNumber,";
							array[0] = employeeNumEmployees.getText();

						} else if (isNumber(employeeNumEmployees.getText()) == false) {

							Text validation = new Text();
							validation.setText("You have mistakenly entered a string in employeeNumber textfield");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						sql += "lastName,";
						array[1] = lastNEmployees.getText();

						sql += "firstName,";
						array[2] = firstNEmployees.getText();

						if (isNumber(officeCEmployees.getText()) == true) {

							sql += "officeCode,";
							array[3] = officeCEmployees.getText();
						} else if (!isNumber(officeCEmployees.getText())) {

							Text validation = new Text();
							validation.setText("You have mistakenly entered a string in officeCode textfield");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						if (!reportsEmployees.getText().isEmpty()) {

							if (isNumber(reportsEmployees.getText())) {

								sql += "reportsTo,";
								array[4] = reportsEmployees.getText();
							} else if (!isNumber(reportsEmployees.getText())) {

								Text validation = new Text();
								validation.setText("You have mistakenly entered a string in reportsTo textfield");
								validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
								validation.setFill(Color.WHITE);
								validation.setTextAlignment(TextAlignment.CENTER);
								fp.setCenter(validation);
								return;
							}

							sql += "jobTitle)";

							sql += "VALUES(";

							for (int i = 0; i < array.length; ++i) {

								if (array[i] != null) {

									sql += "'" + array[i] + "',";
								}
							}
							sql += "'" + jobTEmployees.getText() + "')";
						}

						try {

							System.out.println(sql);

							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels",
									"root", "");
							Statement stmt = con.createStatement();

							stmt.executeUpdate(sql);
							con.close();

						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

						tableView(fp, "SELECT * FROM employees");

					}
				} // employees insert if.
			}

		}); // Insert button setOnAction.

		//////////////////////////
		Button about = new Button();
		about.setText("About");
		about.setMinSize(100, 80);
		about.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

			}
		});

		///////////////////////////
		Button queries = new Button();
		queries.setText(" Execute\nOperation");
		queries.setMinSize(100, 80);
		queries.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (operationSelection == "query1") {

					String sql = "SELECT products.productCode, products.productName FROM products, orderdetails, orders, customers WHERE products.productCode = orderdetails.productCode AND orderdetails.orderNumber = orders.orderNumber AND orders.customerNumber = customers.customerNumber AND";
					if (!countryQueryField.getText().isEmpty() || !cityQueryField.getText().isEmpty()) {

						if (!countryQueryField.getText().isEmpty()) {

							sql += " customers.country like '%" + countryQueryField.getText() + "%' OR";
						}

						if (!cityQueryField.getText().isEmpty()) {

							sql += " customers.city like '%" + cityQueryField + "%'";
						}

						if (sql.endsWith("OR")) {

							String subSql = sql.substring(0, sql.length() - 2);
							sql = subSql;

						} else if (sql.endsWith("AND")) {
							String subSql = sql.substring(0, sql.length() - 3);
							sql = subSql;
						}

						try {
							System.out.println(sql);

							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels",
									"root", "");
							con.close();

						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

						tableView(fp, sql);
					}
				} // query 1 operation selection if

				if (operationSelection == "query2") {

					String sql = "";

					if (!priceQuery.getText().isEmpty()) {

						sql += "SELECT * FROM products WHERE buyPrice > ";
						if (isDouble(priceQuery.getText())) {

							sql += priceQuery.getText();

						} else if (!isDouble(priceQuery.getText())) {

							Text validation = new Text();
							validation.setText("ENTER A DOUBLE VALUE!");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}
					}

					try {
						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					tableView(fp, sql);
				} // if query2

				if (operationSelection == "query3") {

					if (!range1Field.getText().isEmpty() && !range2Field.getText().isEmpty()) {

						String sql = "SELECT COUNT(customers.customerNumber) FROM customers WHERE customers.creditLimit BETWEEN ";
						if (isDouble(range1Field.getText())) {

							sql += range1Field.getText() + " AND ";
						} else if (!isDouble(range1Field.getText())) {

							Text validation = new Text();
							validation.setText("ENTER A DOUBLE VALUE IN BOTH RANGES!");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						if (isDouble(range2Field.getText())) {

							sql += range2Field.getText();
						} else if (!isDouble(range2Field.getText())) {

							Text validation = new Text();
							validation.setText("ENTER A DOUBLE VALUE IN SECOND RANGES!");
							validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
							validation.setFill(Color.WHITE);
							validation.setTextAlignment(TextAlignment.CENTER);
							fp.setCenter(validation);
							return;
						}

						try {
							System.out.println(sql);

							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels",
									"root", "");
							con.close();

						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

						tableView(fp, sql);
					}

				} // if query3

				if (operationSelection == "query5") {

					String sql = "Select * FROM products WHERE productDescription like ";

					if (!key.getText().isEmpty()) {

						sql += "'%" + key.getText() + "%'";
					} else if (key.getText().isEmpty()) {

						Text validation = new Text();
						validation.setText("Please Put A Description Keyword!");
						validation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
						validation.setFill(Color.WHITE);
						validation.setTextAlignment(TextAlignment.CENTER);
						fp.setCenter(validation);
						return;
					}

					try {
						System.out.println(sql);

						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root",
								"");
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					tableView(fp, sql);
				} // if query5.
			}
		});

		operations.getChildren().addAll(search, insert, about, queries);

		fp.setStyle("-fx-background-color: #33FFB8");
		tables.getChildren().addAll(customers, orderDetails, productlines, orders, offices, products, payments,
				employees);

		operations.setSpacing(111);

		fp.setRight(operations);
		fp.setLeft(tables);

		VBox box = new VBox();
		box.getChildren().add(mainBar);
		box.getChildren().add(fp);

		primaryStage.setScene(new Scene(box, 1300, 650));
		primaryStage.show();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("all")
	public static void tableView(BorderPane bp, String rs) {

		TableView tableView = new TableView();

		bp.setCenter(tableView);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "");
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(rs);

			ObservableList<Object> data = FXCollections.observableArrayList();

			/**********************************
			 * TABLE COLUMN ADDED DYNAMICALLY *
			 **********************************/
			for (int i = 0; i < res.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(res.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty((String) param.getValue().get(j));
							}
						});

				tableView.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			/********************************
			 * Data added to ObservableList *
			 ********************************/
			while (res.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= res.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(res.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);

			}

			// FINALLY ADDED TO TableView
			tableView.setItems(data);
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean isNumber(String s) throws NumberFormatException {

		try {
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public static boolean isDouble(String s) throws NumberFormatException {

		try {
			Double.parseDouble(s);
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public static boolean isDate(String s) {

		boolean b;

		if (s.length() == 10) {

			if (isNumber(Character.toString(s.charAt(0))) && isNumber(Character.toString(s.charAt(1)))
					&& isNumber(Character.toString(s.charAt(2))) && isNumber(Character.toString(s.charAt(3)))
					&& s.charAt(4) == '-' && isNumber(Character.toString(s.charAt(5)))
					&& isNumber(Character.toString(s.charAt(6))) && s.charAt(7) == '-'
					&& isNumber(Character.toString(s.charAt(8))) && isNumber(Character.toString(s.charAt(9)))) {

				b = true;
			} else {
				b = false;
			}
			return b;
		} else if (s.length() == 4) {

			if (isNumber(Character.toString(s.charAt(0))) && isNumber(Character.toString(s.charAt(1)))
					&& isNumber(Character.toString(s.charAt(2))) && isNumber(Character.toString(s.charAt(3)))) {

				b = true;
			} else {
				b = false;
			}

			return b;
		} else if (s.length() == 7) {

			if (isNumber(Character.toString(s.charAt(0))) && isNumber(Character.toString(s.charAt(1)))
					&& isNumber(Character.toString(s.charAt(2))) && isNumber(Character.toString(s.charAt(3)))
					&& s.charAt(4) == '-' && isNumber(Character.toString(s.charAt(5)))
					&& isNumber(Character.toString(s.charAt(6)))) {

				b = true;

			} else {
				b = false;
			}

			return b;
		}

		else {

			return false;
		}

	}
}
