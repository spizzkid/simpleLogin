package page;

import dao.userDaoImpl;
import pojo.user;

import java.util.Scanner;

public class userPage {

    private static Scanner input = new Scanner(System.in);

    private static userDaoImpl dao = new userDaoImpl();

    /**
     * 主界面展示
     * @throws Exception
     */
    public static void show() throws Exception {
        System.out.println("欢迎");
        System.out.println("1.新用户注册");
        System.out.println("2.登陆");
        System.out.println("3.登陆后修改密码");
        System.out.println("4.重置密码");
        System.out.println("其他数字:退出系统");
        int i = input.nextInt();
        switch (i){
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                changePassword();
                break;
            case 4:
                resetPassword();
                break;
            default:
                System.exit(-1);
                System.out.println("退出成功");
                break;
        }
    }

    /**
     * 通过用户名重置密码
     * @throws Exception 找不到该用户
     */
    private static void resetPassword() throws Exception {
        System.out.println("输入要修改密码的用户名:");
        String username = input.next();
        user user = dao.selectUser(username);

        user result = new user();
        result.setUsername(user.getUsername());
        result.setQuestion(user.getQuestion());
        result.setAnswer(user.getAnswer());

        System.out.println("你们的密保问题为:"+user.getQuestion());
        System.out.println("请输入答案:");
        String answer = input.next();
        if(answer.equals(user.getAnswer())) {
            System.out.println("答案正确\n输入你想要更改的密码:");
            String changedPassword = input.next();
            result.setPassword(changedPassword);

            int a = dao.update(result);
            if(a>0){
                System.out.println("修改成功");

            }else{
                System.out.println("修改失败");
            }
        }else{
            System.out.println("答案错误!输入任意键重试或输入exit退出到主界面:");
            String userInput = input.next();
            if ("exit".equals(userInput)){
                show();
            }else{
                resetPassword();
            }
        }
        show();
    }

    /**
     * 用户登陆
     * @throws Exception 数据库中没有该用户
     */
    private static void login() throws Exception {
        try {
            System.out.println("输入用户名:");
            String username = input.next();

            user user = dao.selectUser(username);

            System.out.println("输入密码:");
            String password = input.next();
            if (password.equals(user.getPassword())) {
                System.out.println("登陆成功");
                System.out.println("你的用户信息如下");
                System.out.println(user);
                show();
            } else {
                System.out.println("密码错误请重试");
                show();
            }

        }catch(NullPointerException e) {
            throw new Exception("无法找到该用户");
        }

    }

    /**
     * 登陆后修改密码
     * @throws Exception 数据库中没有该用户
     */
    private static void changePassword() throws Exception {
        try {
            System.out.println("输入用户名:");
            String username = input.next();
            user user = dao.selectUser(username);

            user result = new user();
            result.setUsername(user.getUsername());
            result.setQuestion(user.getQuestion());
            result.setAnswer(user.getAnswer());

            System.out.println("输入密码:");
            String password = input.next();
            if (password.equals(user.getPassword())) {
                System.out.println("登陆成功");
                System.out.println("你的用户信息如下");
                System.out.println(user);


                System.out.println("输入你想要更改的密码，不修改输入exit:");
                String changedPassword = input.next();
                if("exit".equals(changedPassword)){
                    result.setPassword(user.getPassword());
                }else{
                    result.setPassword(changedPassword);
                }
                System.out.println(result);

                int a = dao.update(result);
                if(a>0){
                    System.out.println("修改成功");
                }else{
                    System.out.println("修改失败");
                }
                show();

            } else {
                System.out.println("密码错误请重试");
                changePassword();
            }
        }catch(NullPointerException e) {
            throw new Exception("无法找到该用户");
        }

    }
    private static void register() throws Exception {
        System.out.println("创建用户名:");
        String username = input.next();

        System.out.println("创建密码:");
        String password = input.next();

        System.out.println("输入手机号:");
        String phone = input.next();

        System.out.println("输入邮箱:");
        String email = input.next();

        System.out.println("设置问题:");
        String question = input.next();
        System.out.println("设置答案:");
        String answer = input.next();

        user user = new user();

        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setQuestion(question);
        user.setAnswer(answer);


        int add = dao.add(user);
        if(add>0) {
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }
        show();

    }


}
