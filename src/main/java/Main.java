import utils.PostObject;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static apiCalls.ApiCalls.*;
import static utils.Utils.convertResponseToListOfObjects;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner input = new Scanner(System.in);

        List<PostObject> startBankId = convertResponseToListOfObjects(getAuthMethodAsString());
        System.out.println("please enter a personalnumber for bank id login");
        // I could not get this to work.
        postBankIdLogin(input.next(), startBankId, getCookies());
            //Thread.Sleep is not great.. could not get it working with timer..
        for (int i = 0; i < 10; i++) {
            System.out.println(checkStatus(getCookies()));
            Thread.sleep(1000);

        }




    }

}
