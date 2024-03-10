package book;

import java.util.Scanner;

public class Library
{
    private static int categoryCount = 0;
    private static int bookCount = 0;

    public static void main(String[] args)
    {
        Category[] categories = new Category[10];
        Book[] books = new Book[100];
        for (int i = 0; i < bookCount; i++)
        {
            boolean catExisted = false;
            for (int j = 0; j < bookCount; j++)
            {
                if (categories[i].getId() == books[i].getCategoryCode())
                {
                    catExisted = true;
                    break;
                }
            }
            if (!catExisted)
            {
                categories[i] = books[i].getCategory();//Thêm thể loại sách vào dựa trên sách đã tạo
                categoryCount++;
            }
        }
        Scanner scanner = new Scanner(System.in);
        ManageLibrary(scanner, categories, books);
    }

    private static void ManageLibrary(Scanner scanner, Category[] categories, Book[] books)
    {
        System.out.println("=============QUẢN LÝ THƯ VIỆN===============");
        System.out.println("\t 1. Quản lý thể loại");
        System.out.println("\t 2. Quản lý Sách");
        System.out.println("\t 0. Thoát");
        System.out.println("Mời nhập lựa chọn");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice)
        {
            case 1:
                ManageCategory(scanner, categories, books);
                break;
            case 2:
                ManageBook(scanner);
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Lựa chọn không khả dụng");
                break;
        }
    }

    private static void ManageCategory(Scanner scanner, Category[] categories, Book[] books)
    {
        System.out.println("===========QUẢN LÝ THỂ LOẠI===========");
        System.out.println("\t 1. Thêm mới thể loại");
        System.out.println("\t 2. Hiển thị danh sách theo tên thể loại từ A-Z");
        System.out.println("\t 3. Thống kê thể loại và số sách có trong mỗi thể loại");
        System.out.println("\t 4. Cập nhật thể loại");
        System.out.println("\t 5. Xóa thể loại (kiểm tra tồn tại sách trước khi xóa)");
        System.out.println("\t 0. Quay lại");
        System.out.println("Mời nhập lựa chọn");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice)
        {
            case 1:
                Category newCat = new Category();
                newCat.inputData(scanner, categories);
                categories[categoryCount] = newCat;
                categoryCount++;
                break;
            case 2:
                for (int i = 0; i < categoryCount; i++)
                {//Bubble sort base on 1st char in category name (which is a string)
                    for (int j = 0; j < categoryCount; j++)
                    {
                        if ((int) categories[j].getName().charAt(0) > (int) categories[j + 1].getName().charAt(0))
                        {
                            Category temp = categories[j];
                            categories[j] = categories[j + 1];
                            categories[j + 1] = temp;
                        }
                    }
                }
                for (Category c : categories)
                {
                    c.displayData();
                }
                break;
            case 3:
                ShowCategories(categories, books);
                break;
            case 4:
                System.out.println("Nhập mã thể loại muốn cập nhật");
                int idUpdate = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < categoryCount; i++)
                {
                    if (categories[i].getId() == idUpdate)
                    {
                        System.out.println("Mời cập nhật");
                        categories[i].inputData(scanner, categories);
                        break;
                    }
                }
                break;
            case 5:
                System.out.println("Nhập mã thể loại muốn xóa");
                int idDelete = Integer.parseInt(scanner.nextLine());
                boolean canDelete = true;
                for (int i = 0; i < categoryCount; i++)
                {
                    if (idDelete == categories[i].getId())
                    {
                        for (int j = 0; j < bookCount; j++)
                        {
                            if (books[j].getCategoryCode() == idDelete)
                            {
                                System.out.println("Trong thể loại này đang có sách, không thể xóa");
                                canDelete = false;
                                break;
                            }
                        }
                        if (canDelete)
                        {
                            categories[i] = null;
                            for (int k = i; k < categoryCount - 1; k++)
                            {//Shift elements up
                                categories[k] = categories[k + 1];
                            }
                            categories[categoryCount] = null;
                        }
                        break;
                    }
                }
                break;
            case 0:
                ManageLibrary(scanner, categories, books);
            default:
                System.out.println("Lựa chọn không khả dụng");
                break;
        }
    }

    private static void ManageBook(Scanner scanner, Category[] categories, Book[] books)
    {
        System.out.println("=========QUẢN LÝ SÁCH==========");
        System.out.println("\t 1. Thêm mới sách");
        System.out.println("\t 2. Cập nhật thông tin sách");
        System.out.println("\t 3. Xóa sách");
        System.out.println("\t 4. Tìm kiếm sách");
        System.out.println("\t 5. Hiển thị danh sách theo nhóm thể loại");
        System.out.println("\t 0. Quay lại");
        System.out.println("Mời nhập lựa chọn");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice)
        {
            case 1:
                Book newBook = new Book();
                newBook.inputData(scanner, books, categories);
                books[bookCount] = newBook;
                break;
            case 2:
                Book bookToUpdate = FindBookIndex(scanner, books);
                if (bookToUpdate != null)
                {
                    bookToUpdate.inputData(scanner, books, categories);
                } else System.out.println("Sách không tồn tại");
                break;
            case 3:
                Book bookToDelete = FindBookIndex(scanner, books);
                if (bookToDelete != null)
                {
                    bookToDelete = null;
                    for (int i = 0; i < bookCount - 1; i++)
                    {
                        books[i] = books[i + 1];
                    }
                    books[bookCount] = null;
                } else System.out.println("Sách không tồn tại");
                break;
            case 4:
                System.out.println("Mời nhập từ khóa tìm kiếm");
                String searchInput = scanner.nextLine();
                boolean bookIsFound = false;
                for (Book book : books)
                {
                    if (book.getTitle().contains(searchInput) || book.getAuthor().contains(searchInput))
                    {
                        System.out.println("Sách có thông tin giống với nội dung tìm kiếm:");
                        book.displayData();
                        bookIsFound = true;
                    }
                }
                if (!bookIsFound)
                    System.out.println("Không tìm thấy sách nào thỏa mãn yêu cầu");
                break;
            case 5:
                ShowCategories(categories, books);
                break;
            case 0:
                ManageLibrary(scanner, categories, books);
            default:
                System.out.println("Lựa chọn không khả dụng");
                break;
        }
    }

    private static void ShowCategories(Category[] categories, Book[] books)
    {
        System.out.println("Danh sách các thể loại sách: ");
        for (int i = 0; i < categoryCount; i++)
        {
            System.out.println(categories[i].getName());//Hiển thị tên thể loại
            for (int j = 0; j < bookCount; j++)
            {
                if (books[j].getCategory() == categories[i])
                {
                    books[j].displayData();//Nếu sách trùng thể loại thì hiển thị ra
                }
            }
        }
    }

    private static Book FindBookIndex(Scanner scanner, Book[] books)
    {
        System.out.println("Nhập mã sách cần tìm");
        String input = scanner.nextLine();
        for (int i = 0; i < bookCount; i++)
        {
            if (books[i].getId().equals(input))
            {
                return books[i];
            }
        }
        return null;
    }
}
