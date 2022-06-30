package page;

import dao.userDaoImpl;
import pojo.user;

import java.util.Scanner;

public class userPage {

    private static Scanner input = new Scanner(System.in);

    private static userDaoImpl dao = new userDaoImpl();

    /**
     * ������չʾ
     * @throws Exception
     */
    public static void show() throws Exception {
        System.out.println("��ӭ");
        System.out.println("1.���û�ע��");
        System.out.println("2.��½");
        System.out.println("3.��½���޸�����");
        System.out.println("4.��������");
        System.out.println("��������:�˳�ϵͳ");
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
                System.out.println("�˳��ɹ�");
                break;
        }
    }

    /**
     * ͨ���û�����������
     * @throws Exception �Ҳ������û�
     */
    private static void resetPassword() throws Exception {
        System.out.println("����Ҫ�޸�������û���:");
        String username = input.next();
        user user = dao.selectUser(username);

        user result = new user();
        result.setUsername(user.getUsername());
        result.setQuestion(user.getQuestion());
        result.setAnswer(user.getAnswer());

        System.out.println("���ǵ��ܱ�����Ϊ:"+user.getQuestion());
        System.out.println("�������:");
        String answer = input.next();
        if(answer.equals(user.getAnswer())) {
            System.out.println("����ȷ\n��������Ҫ���ĵ�����:");
            String changedPassword = input.next();
            result.setPassword(changedPassword);

            int a = dao.update(result);
            if(a>0){
                System.out.println("�޸ĳɹ�");

            }else{
                System.out.println("�޸�ʧ��");
            }
        }else{
            System.out.println("�𰸴���!������������Ի�����exit�˳���������:");
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
     * �û���½
     * @throws Exception ���ݿ���û�и��û�
     */
    private static void login() throws Exception {
        try {
            System.out.println("�����û���:");
            String username = input.next();

            user user = dao.selectUser(username);

            System.out.println("��������:");
            String password = input.next();
            if (password.equals(user.getPassword())) {
                System.out.println("��½�ɹ�");
                System.out.println("����û���Ϣ����");
                System.out.println(user);
                show();
            } else {
                System.out.println("�������������");
                show();
            }

        }catch(NullPointerException e) {
            throw new Exception("�޷��ҵ����û�");
        }

    }

    /**
     * ��½���޸�����
     * @throws Exception ���ݿ���û�и��û�
     */
    private static void changePassword() throws Exception {
        try {
            System.out.println("�����û���:");
            String username = input.next();
            user user = dao.selectUser(username);

            user result = new user();
            result.setUsername(user.getUsername());
            result.setQuestion(user.getQuestion());
            result.setAnswer(user.getAnswer());

            System.out.println("��������:");
            String password = input.next();
            if (password.equals(user.getPassword())) {
                System.out.println("��½�ɹ�");
                System.out.println("����û���Ϣ����");
                System.out.println(user);


                System.out.println("��������Ҫ���ĵ����룬���޸�����exit:");
                String changedPassword = input.next();
                if("exit".equals(changedPassword)){
                    result.setPassword(user.getPassword());
                }else{
                    result.setPassword(changedPassword);
                }
                System.out.println(result);

                int a = dao.update(result);
                if(a>0){
                    System.out.println("�޸ĳɹ�");
                }else{
                    System.out.println("�޸�ʧ��");
                }
                show();

            } else {
                System.out.println("�������������");
                changePassword();
            }
        }catch(NullPointerException e) {
            throw new Exception("�޷��ҵ����û�");
        }

    }
    private static void register() throws Exception {
        System.out.println("�����û���:");
        String username = input.next();

        System.out.println("��������:");
        String password = input.next();

        System.out.println("�����ֻ���:");
        String phone = input.next();

        System.out.println("��������:");
        String email = input.next();

        System.out.println("��������:");
        String question = input.next();
        System.out.println("���ô�:");
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
            System.out.println("ע��ɹ�");
        }else{
            System.out.println("ע��ʧ��");
        }
        show();

    }


}
