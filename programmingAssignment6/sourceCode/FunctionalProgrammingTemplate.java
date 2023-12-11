/*
* Title: Programming Assignment 6 - Functional Programming
* Author: James Taddei
* Date: 2023-10-23
* Dscription:
*   This program creates a list of invoices and displays the list via streams
*   after various operations.
*/

package functionalprogrammingtemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author acv
 */
public class FunctionalProgrammingTemplate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creates the ArrayList of Invoices
        List<Invoice> invoices = List.of(
        new Invoice(83,"Electric sander", 7, 57.98),
        new Invoice(24,"Power saw", 18, 99.99),
        new Invoice(7,"Sledge hammer", 11, 21.50),
        new Invoice(77,"Hammer", 76, 11.99),
        new Invoice(39,"Lawn mower", 3, 79.50),
        new Invoice(68,"Screw driver", 106, 6.99),
        new Invoice(56,"Jig saw", 21, 11.00),
        new Invoice(3,"Wrench", 34, 7.50));
        
        // Displays the table of invoices.
        System.out.println("Part number\tPart description\tQuantity\tPrice per"
                + " item\t   Value");
        invoices.stream()
                .forEach(System.out::print);
        
        // a) Sorts the invoices by partDescription then displays the results.
        System.out.println("\nInvoices sorted by Part description");
        System.out.println("Part number\tPart description\tQuantity\tPrice per"
                + " item\t   Value");
        invoices.stream()
                .sorted(Comparator.comparing(Invoice::getPartDescription))
                .forEach(System.out::print);
        
        // b) Sorts the invoices by price then displays the results.
        System.out.println("\nInvoices sorted by Price");
        System.out.println("Part number\tPart description\tQuantity\tPrice per"
                + " item\t   Value");
        invoices.stream()
                .sorted(Comparator.comparing(Invoice::getPricePerItem))
                .forEach(System.out::print);
        
        // c) Maps each invoice to a partDescription and quantity before sorting
        // by quantity and displaying the results.
        System.out.println("\nPart Description and Quantity for each Invoice");
        System.out.println("Part description\tQuantity");
        // Stores the invoice list as a map.
        Map<String, Integer> mapDescriptionAndQuantity =
                invoices.stream()
                        .collect(Collectors.toMap(Invoice::getPartDescription,
                                Invoice::getQuantity));
        // Sorts and outputs the invoice map.
        mapDescriptionAndQuantity.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(item -> {
                    System.out.printf("%-23s%8d%n", item.getKey(),
                            item.getValue());
                });
    }
}
