package book;

import java.time.Year;
import java.util.Scanner;

public class Book
{
    private String id, title, author, description;
    private int year;
    private Category category;
    private int categoryCode;

    public int getCategoryCode()
    {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode)
    {
        this.categoryCode = categoryCode;
    }

    public Book(String id, String title, String author, String description, int year, Category category)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.year = year;
        this.category = category;
    }

    public Book()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public void inputData(Scanner scanner, Book[] books, Category[] categories)
    {
        while (true)//Book name
        {
            System.out.println("Nhập mã sách bắt đầu bằng B và có 4 ký tự");
            String idInput = scanner.nextLine();
            boolean idExist = false;
            if (!idInput.startsWith("B"))
            {
                System.out.println("Mã sách phải bắt đầu bằng B");
            } else if (idInput.length() != 4)
            {
                System.out.println("Mã sách phải có 4 ký tự");
            } else
            {
                this.id = idInput;
                for (Book book : books)
                {
                    if (book.getId().equals(idInput))
                    {
                        System.out.println("Mã sách này đã tồn tại");
                        idExist = true;
                    }
                }
                if (!idExist)
                    break;
            }
        }
        while (true)//Book title
        {
            System.out.println("Nhập tiêu đề sách, từ 6-50 ký tự");
            String titleInput = scanner.nextLine();
            boolean titleExist = false;
            if (titleInput.length() < 6 || titleInput.length() > 50)
            {
                System.out.println("Tiêu đề sách phải có độ dài từ 6-50 ký tự");
            } else
            {
                for (Book book : books)
                {
                    if (book.getTitle().equals(titleInput))
                    {
                        System.out.println("Mã sách này đã tồn tại");
                        titleExist = true;
                    }
                }
                if (!titleExist)
                    break;
            }
        }
        while (true) //Author name
        {
            System.out.println("Mời nhập tên tác giả");
            String authorInput = scanner.nextLine();
            if (authorInput.isBlank())
            {
                System.out.println("Tên tác giả không được để trống");
            } else break;
        }
        while (true)// Book year
        {
            System.out.println("Mời nhập năm xuất bản của sách");
            int yearInput = Integer.parseInt(scanner.nextLine());
            if (yearInput < 1970 || yearInput > Year.now().getValue())
            {
                System.out.println("Năm xuất bản không được nhỏ hơn 1970 hoặc lớn hơn hiện tại");
            } else break;
        }
        while (true) //Book description
        {
            System.out.println("Mời nhập mô tả sách");
            String descriptionInput = scanner.nextLine();
            if (descriptionInput.isBlank())
            {
                System.out.println("Mô tả sách không được để trống");
            } else break;
        }
        while (true)// Category
        {
            System.out.println("Mời nhập mã thể loại sách");
            int codeInput = Integer.parseInt(scanner.nextLine());
            boolean codeExist = false;
            for (Category c : categories)
            {
                if (c.getId() == codeInput)
                {
                    this.categoryCode = codeInput;
                    this.category = c;//Gán thể loại sách dựa theo mã thể loại
                    codeExist = true;
                    break;
                }
            }
            if (!codeExist)
            {
                System.out.println("Mã thể loại này không tồn tại, vui lòng nhập lại");
            } else break;
        }
    }

    public void displayData()
    {
        System.out.println("Mã sách: " + this.id);
        System.out.println("Tiêu đề sách: " + this.title);
        System.out.println("Tên tác giả: " + this.author);
        System.out.println("Năm xuất bản sách: " + this.year);
        System.out.println("Thể loại sách: " + this.category.getName());
    }
}
