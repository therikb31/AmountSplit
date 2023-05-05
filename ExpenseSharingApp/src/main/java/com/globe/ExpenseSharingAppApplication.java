
package com.globe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.globe.controller.AppUserController;
import com.globe.controller.TransactionController;
import com.globe.model.AppUser;
import com.globe.model.Transaction;

@SpringBootApplication
public class ExpenseSharingAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ExpenseSharingAppApplication.class, args);

		AppUserController appUserController = context.getBean(AppUserController.class);
		TransactionController transactionController = context.getBean(TransactionController.class);
		Transaction transaction = new Transaction();

		AppUser u1 = new AppUser("u1", "User1", "therikb31@gmail.com", "9679340026");
		appUserController.addUser(u1);

		AppUser u2 = new AppUser("u2", "User2", "john@123", "1234");
		appUserController.addUser(u2);

		AppUser u3 = new AppUser("u3", "User3", "smith@123", "1235");
		appUserController.addUser(u3);

		AppUser u4 = new AppUser("u4", "User4", "will@123", "1236");
		appUserController.addUser(u4);

		Scanner in = new Scanner(System.in);
		System.out.println("Start your Commands from here!");
		Boolean flag = true;
		while (flag) {
			String command = in.nextLine();
			String[] commands = command.split(" ");
			int index = 0;
			String operation = commands[index++];

			switch (operation) {

			case "SHOW":
				switch (commands.length) {

				case 1:
					transactionController.show();
					break;

				case 2:
					String userId = commands[index++];
					transactionController.show(userId);
					break;

				default:
					System.out.println("Expected Number of Agruments Exceeded!");
				}
				break;

			case "EXPENSE":
				if (commands.length > 1) {
					List<AppUser> list = new ArrayList<>();
					List<Double> weights = new ArrayList<>();
					double sum = 0;

					String userId = commands[index++];
					AppUser splitBy = appUserController.getAppUserById(userId);
					transaction.setSplitBy(splitBy);

					if (splitBy != null) {

						double totalAmount = Double.parseDouble(commands[index++]);
						transaction.setTotalAmount(totalAmount);

						int userCount = Integer.parseInt(commands[index++]);
						transaction.setUserCount(userCount);

						for (int i = 0; i < userCount; i++) {
							String splitToId = commands[index++];
							AppUser splitTo = appUserController.getAppUserById(splitToId);
							if (splitTo != null)
								list.add(splitTo);
							else {
								System.out.println("User " + splitToId + " doesnot exist!");
							}
						}
						transaction.setSplitTo(list);

						String splitType = commands[index++];
						transaction.setSplitType(splitType);
						switch (splitType) {

						case "EQUAL":
							
							transactionController.execute(transaction);
							break;

						case "EXACT":
							for (int i = 0; i < userCount; i++) {
								double val = Double.parseDouble(commands[index++]);
								weights.add(val);
								sum += val;
							}
							if (sum != totalAmount) {
								System.out.println("Amounts donot add up to the Total Amount!");
								break;
							} else {
								transaction.setWeights(weights);
							}

							transactionController.execute(transaction);
							break;

						case "PERCENT":
							for (int i = 0; i < userCount; i++) {
								double val = Double.parseDouble(commands[index++]);
								weights.add(val);
								sum += val;
							}
							if (sum != 100) {
								System.out.println("Percentages donot add up to 100!");
								break;
							} else {
								transaction.setWeights(weights);
							}
							transactionController.execute(transaction);
							break;

						default:
							System.out.println("Use EQUAL, EXACT or PERCENT!");
							break;
						}
					} else
						System.out.println("User doesnot exist!");
				} else {
					System.out.println("Agruments Insufficient!");
				}
				break;

			default:
				System.out.println("Invalid Input! Use keywords like EXPENSE or SHOW");
				
				break;
			}
		}
	}

}
