package com.example.calculator2;

public class Calculations {

    public String answer(String f) {
        double answer = 0;
        int index = 0;
        double valueOne = 0;
        double valueTwo = 0;
        Boolean add = false,
                mult = false,
                div = false,
                sub = false;
        if (f.indexOf("+") != -1) {
            add = true;
        }
        if (f.indexOf("-") != -1) {
            sub = true;
        }
        if (f.indexOf("÷") != -1) {
            div = true;
        }
        if (f.indexOf("x") != -1) {
            mult = true;
        }
        String t;
        t = "a" + f.replaceAll("[^0-9.E]", "a") + "a";
        t = t.replaceAll("[E]", "0");
        f = f.replaceAll("[E]", "0");//checks to end everything
//checks to end everything

        while (mult || div) {
            answer = 0;

            if ((mult && !div) || mult && f.indexOf("x") < f.indexOf("÷")) {
                index = f.indexOf("x");
                System.out.println(f + "   2");

                valueOne = Double.parseDouble(t.substring(t.lastIndexOf("a", index) + 1, index + 1));
                valueTwo = Double.parseDouble(t.substring(index + 2, t.indexOf("a", index + 2)));

                answer = valueOne * valueTwo;

                if (t.lastIndexOf("a", index) == 0) {                                                 //replace expression just solved with its answ in expression string
                    f = answer + f.substring(t.indexOf("a", index + 2) - 1);
                } else {
                    f = f.substring(0, t.lastIndexOf("a", index - 1)) + answer + f.substring(t.indexOf("a", index + 2) - 1);
                }
                System.out.println(f + "mult1");
                System.out.println(t + "mult1");
                t = "a" + f.replaceAll("[^0-9.E]", "a") + "a";
                t = t.replaceAll("[E]", "0");
                f = f.replaceAll("[E]", "0");//checks to end everything
//checks to end everything

//checks to end everything
                System.out.println(f + "   3");

                if (f.indexOf("x") == -1) {
                    mult = false;
                }

            } else {
                index = f.indexOf("÷");
                System.out.println(f + "   4");

                valueOne = Double.parseDouble(t.substring(t.lastIndexOf("a", index) + 1, index + 1));
                valueTwo = Double.parseDouble(t.substring(index + 2, t.indexOf("a", index + 2)));

                answer = valueOne / valueTwo;

                if (t.lastIndexOf("a", index) == 0) {                                                 //replace expression just solved with its answ in expression string
                    f = answer + f.substring(t.indexOf("a", index + 2) - 1);
                } else {
                    f = f.substring(0, t.lastIndexOf("a", index - 1)) + answer + f.substring(t.indexOf("a", index + 2) - 1);
                }
                t = "a" + f.replaceAll("[^0-9.E]", "a") + "a";
                t = t.replaceAll("[E]", "0");
                f = f.replaceAll("[E]", "0");//checks to end everything
//checks to end everything
//checks to end everything
                System.out.println(f + "   5");

                if (f.indexOf("÷") == -1) {  //checks to end everything
                    div = false;
                }
            }
        }
        System.out.println(f + "   6");
        while (add || sub) {
            if ((add && !sub) || add && f.indexOf("+") < f.indexOf("-")) {
                index = f.indexOf("+");


                valueOne = Double.parseDouble(t.substring(t.lastIndexOf("a", index) + 1, index + 1));
                valueTwo = Double.parseDouble(t.substring(index + 2, t.indexOf("a", index + 2)));

                answer = valueOne + valueTwo;

                if (t.lastIndexOf("a", index) == 0) {                                                 //replace expression just solved with its answ in expression string
                    f = answer + f.substring(t.indexOf("a", index + 2) - 1);
                } else {
                    f = f.substring(0, t.lastIndexOf("a", index - 1)) + answer + f.substring(t.indexOf("a", index + 2) - 1);
                }
                System.out.println(f + "mult1");
                System.out.println(t + "mult1");
                t = "a" + f.replaceAll("[^0-9.E]", "a") + "a";
                t = t.replaceAll("[E]", "0");
                f = f.replaceAll("[E]", "0");//checks to end everything
//checks to end everything
//checks to end everything

                if (f.indexOf("+") == -1) {
                    add = false;
                }

            } else {

                index = f.indexOf("-");
                if (index == 0) {

                    System.out.println(f + " s1");

                    System.out.println(index + " s1");

                    System.out.println(t.substring(1, t.indexOf("a", index + 2)) + "ssssub");
                    System.out.println(f.indexOf("+", index + 1) + "true index");

                    valueOne = Double.parseDouble(f.substring(0, t.indexOf("a", index + 2) - 1));
                    if ( add && f.indexOf("+", index + 1) < f.indexOf("-", index + 1)) {
                        index = f.indexOf("+", index + 1);

                    } else if (f.indexOf("+", index + 1) > -1 && f.indexOf("-", index + 1) == -1) {

                        index = f.indexOf("+", index + 1);

                    } else {
                        index = f.indexOf("-", index + 1);

                    }
                    System.out.println(index + "true index");
                    if (index == -1) {
                        return f;

                    }
                    valueTwo = Double.parseDouble(f.substring(index, t.indexOf("a", index + 2) - 1));       //FOCUS

                    answer = valueOne + valueTwo;


                    //replace expression just solved with its answ in expression string
                    f = answer + f.substring(t.indexOf("a", index + 2) - 1);
                    t = "a" + f.replaceAll("[^0-9.E]", "a") + "a";//checks to end everything
                    t = t.replaceAll("[E]", "0");
                    f = f.replaceAll("[E]", "0");//checks to end everything
//checks to end everything
                    System.out.println(valueOne + "kit");

                    System.out.println(valueTwo + "catttt");
                    System.out.println(answer + "  index");
                    System.out.println(f + "  fin");


                    if (!f.contains("-") && !f.contains("+")) {  //checks to end everything
                        sub = false;
                        add = false;
                    } else if (!f.contains("+")) {
                        add = false;

                    }

                } else {
                    System.out.println(f + " s2");

                    System.out.println(index + " s2");
                    System.out.println(t.substring(t.lastIndexOf("a", index) + 1, index + 1) + " s");

                    valueOne = Double.parseDouble(t.substring(t.lastIndexOf("a", index) + 1, index + 1));
                    valueTwo = Double.parseDouble(t.substring(index + 2, t.indexOf("a", index + 2)));

                    answer = valueOne - valueTwo;

                    if (t.lastIndexOf("a", index) == 0) {                                                 //replace expression just solved with its answ in expression string
                        f = answer + f.substring(t.indexOf("a", index + 2) - 1);
                    } else {
                        f = f.substring(0, t.lastIndexOf("a", index - 1)) + answer + f.substring(t.indexOf("a", index + 2) - 1);
                    }
                    t = "a" + f.replaceAll("[^0-9.E]", "a") + "a";//checks to end everything
                    t = t.replaceAll("[E]", "0");
                    f = f.replaceAll("[E]", "0");//checks to end everything
//checks to end everything

                    if (f.indexOf("-") == -1) {  //checks to end everything
                        sub = false;
                    }
                }
            }
        }

        return f;


    }
}