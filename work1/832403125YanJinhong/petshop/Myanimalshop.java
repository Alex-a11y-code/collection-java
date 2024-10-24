package com.YJH.west2.q.chongwudianself;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Myanimalshop implements AnimalShop{
    private double balance;//���
    private double profit;//����
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;


    public Myanimalshop(double balance) {
        this.balance = balance;
        this.profit = 0;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException{
        if (this.balance < animal.getBuyingprice()) {
            throw new InsufficientBalanceException(
                    "����, �޷�������; ��ǰ���: " + this.balance + ", ����۸�: " + animal.getBuyingprice()
            );
        }
        // ����㹻, �۳����
        this.balance -= animal.getBuyingprice();
        // ��Ӷ���
        this.animalList.add(animal);
        System.out.println("���̵깺���ˡ� " + animal);
        System.out.println("����ǰ��  " + this.balance);

    }

    @Override
    public void greetCustomer(Customer customer, Animal animal) throws AnimalNotFoundException, IllegalStateException{
        if (this.isOpen==false) {
            System.out.println("�̵���Ъҵ, �޷��д��ͻ�");
            throw new IllegalStateException("�̵���Ъҵ");
        }


        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("�����б�Ϊ��, �޷��д��ͻ�");
        }


        if (!animalList.contains(animal)) {
            throw new AnimalNotFoundException("�����б���û����Ϊ�� " + animal.getName() + " ���Ķ���, �޷��д��ͻ�");
        }
        // ���δ������, �������
        if (animal instanceof Chinesedog) {
            Chinesedog dog = (Chinesedog) animal;
            if (dog.isVaccineInjected==false) {
                dog.setVaccineInjected(true);
                System.out.println("Ϊ" + dog.getName() + "��������");
            }
        }


        if (!customerList.contains(customer)) {
            customerList.add(customer);
        }
        this.profit += animal.getSellingprice() - animal.getBuyingprice();
        this.balance += animal.getSellingprice();
        this.animalList.remove(animal);
        // ���¹˿���Ϣ
        customer.setVisitcount(customer.getVisitcount() + 1);
        customer.setLastvisitdate(LocalDate.now());

        System.out.println("���д��ͻ���  [�ͻ�]: " + customer + " ====> [������]: " + animal);
    }

    @Override
    public void close() {
        this.isOpen = false;
        // ��ӡ����
        System.out.println("���̵���Ъҵ�� ����Ϊ: " + this.profit);
        profit = 0;
    }

    @Override
    public void open() {
        this.isOpen = true;
        System.out.println(" ============ �̵��ѿ�ҵ ============ ");
        System.out.println("����ǰ���:�� " + this.balance);
        System.out.println("����ǰ�����б�:�� " + this.animalList);
        System.out.println("����ǰ�˿��б�:�� " + this.customerList);
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
