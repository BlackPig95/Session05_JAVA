package book;

import java.util.Scanner;

public class Category
{
    private int id;
    private String name;
    private boolean status;

    public Category(int id, String name, boolean status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Category()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void inputData(Scanner scanner, Category[] categories)
    {
        while (true)//ID
        {
            System.out.println("Mời nhập id thể loại sách");
            int idInput = Integer.parseInt(scanner.nextLine());
            boolean idExist = false;
            for (Category category : categories)
            {
                if (idInput == category.getId())
                {
                    System.out.println("Tên này đã tồn tại, mời nhập lại");
                    idExist = true;
                }
            }
            this.id = idInput;
            if (!idExist)
                break;
        }
        while (true)//Name
        {
            System.out.println("Mời nhập tên thể loại, độ dài từ 6-30 ký tự");
            String nameInput = scanner.nextLine();
            boolean nameExist = false;
            for (Category category : categories)
            {
                if (category.getName().equals(nameInput))
                {
                    System.out.println("Tên này đã tồn tại");
                    nameExist = true;
                }
            }
            this.name = nameInput;
            if (!nameExist)
                break;
        }
        while (true)//Status
        {
            System.out.println("Nhập trạng thái của thể loại (chỉ được viết true hoặc false)");
            String statusInput = scanner.nextLine();
            if (statusInput.equals("true") || statusInput.equals("false"))
            {
                this.status = Boolean.parseBoolean(statusInput);
                break;
            } else System.out.println("Vui lòng nhập đúng chữ true hoặc false");
        }
    }

    public void displayData()
    {
        System.out.println("Mã thể loại: " + this.id);
        System.out.println("Tên thể loại: " + this.name);
        System.out.println("Trạng thái thể loại: " + (this.status ? "Đang hoạt động" : "Không hoạt động"));
    }
}
