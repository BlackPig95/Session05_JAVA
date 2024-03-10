package ra.manager;

import ra.model.Product;

import java.util.Scanner;

public class ProductManager
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Product[] products = new Product[100];
        int productCount = 0;
        while (true)
        {
            System.out.println("============MENU==============");
            System.out.println("1. Nhập thông tin cho n sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp sản phẩm theo thứ tự lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo khoảng giá bán");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Cập nhật số lượng dựa trên mã sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm theo mã sản phẩm");
            System.out.println("10. Xóa sản phẩm dựa trên mã sản phẩm");
            System.out.println("0. Thoát");
            System.out.println("--------------------------------------------------");
            System.out.println("Mời chọn thao tác muốn thực hiện");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice)
            {
                case 1:
                    System.out.println("Nhập số lượng sản phẩm muốn thêm thông tin");
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++)
                    {
                        System.out.println("Mời nhập thông tin của sản phẩm thứ " + (i + 1));
                        Product newProduct = new Product();
                        newProduct.inputData(scanner, products);
                        products[productCount] = newProduct;
                        productCount++;
                    }
                    break;
                case 2:
                    if (productCount <= 0)
                    {
                        System.out.println("Hiện không có sản phẩm nào để hiển thị");
                        break;
                    }
                    for (int i = 0; i < productCount; i++)
                    {
                        products[i].displayData();
                    }
                    break;
                case 3:
                    if (productCount <= 0)
                    {
                        System.out.println("Hiện không có sản phẩm nào để tính lợi nhuận");
                        break;
                    }
                    for (int i = 0; i < productCount; i++)
                    {
                        System.out.printf("Lợi nhuận của sản phẩm thứ %d là: \t", i);
                        products[i].callProfit();
                    }
                    break;
                case 4:
                    if (productCount <= 0)
                    {
                        System.out.println("Hiện không có sản phẩm nào để sắp xếp");
                        break;
                    }
                    for (int i = 0; i < productCount - 1; i++)
                    {
                        Product temp = products[i];
                        for (int j = i + 1; j < productCount; j++)
                        {
                            if (temp.getProfit() < products[j].getProfit())
                            {
                                products[j - 1] = products[j];
                                products[j] = temp;
                            } else
                            {
                                products[j - 1] = products[j];
                            }
                        }
                    }
                    for (Product p : products)
                    {
                        p.displayData();
                    }
                    break;
                case 5:
                    System.out.println("Nhập khoảng giá muốn kiểm tra");
                    System.out.println("Giá nhỏ nhất: ");
                    float fromPrice = Float.parseFloat(scanner.nextLine());
                    System.out.println("Giá lớn nhất: ");
                    float toPrice = Float.parseFloat(scanner.nextLine());
                    int priceCount = 0;
                    for (Product product : products)
                    {
                        if (product.getExportPrice() >= fromPrice && product.getExportPrice() <= toPrice)
                        {
                            System.out.println("Tìm thấy sản phẩm có giá bán trong khoảng yêu cầu");
                            product.displayData();
                            System.out.println("----------------------------");
                            priceCount++;
                        }
                    }
                    if (priceCount == 0)
                        System.out.println("Không có sản phẩm nào trong khoảng giá này");
                    break;
                case 6:
                    FindProductName(scanner, products);
                    break;
                case 7:
                    String IdInput = scanner.nextLine();
                    boolean IdExist = false;
                    for (Product product : products)
                    {
                        if (product.getProductId().equals(IdInput))
                        {
                            System.out.println("Mời nhập số lượng muốn thêm vào sản phẩm");
                            int addedQuantity = Integer.parseInt(scanner.nextLine());
                            product.setQuantity(product.getQuantity() + addedQuantity);
                            System.out.println("Số lượng đã cập nhật là :" + product.getQuantity());
                            IdExist = true;
                        }
                    }
                    if (!IdExist)
                        System.out.println("Không có sản phẩm nào có mã này");
                    break;
                case 8:
                    String productName = FindProductName(scanner, products);
                    for (Product product : products)
                    {
                        if (product.getProductName().equals(productName))
                        {
                            System.out.println("Mời nhập số lượng cần bán");
                            int sellQuantity = Integer.parseInt(scanner.nextLine());
                            if (!product.isStatus())
                            {
                                System.out.println("Sản phẩm hiện không bán");
                            } else if (sellQuantity > product.getQuantity())
                            {
                                System.out.println("Số lượng hàng không đủ");
                            } else
                            {
                                product.setQuantity(product.getQuantity() - sellQuantity);
                            }
                        }
                    }
                    break;
                case 9:
                    String IdSearch = scanner.nextLine();
                    boolean productExist = false;
                    for (Product product : products)
                    {
                        if (product.getProductId().equals(IdSearch))
                        {
                            product.setStatus(!product.isStatus());
                            productExist = true;
                        }
                    }
                    if (!productExist)
                        System.out.println("Sản phẩm không tồn tại");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }
    }

    private static String FindProductName(Scanner scanner, Product[] products)
    {
        System.out.println("Nhập tên sản phẩm muốn tìm");
        String nameInput = scanner.nextLine();
        boolean nameExist = false;
        for (Product product : products)
        {
            if (product.getProductName().equals(nameInput))
            {
                System.out.println("Tìm thấy sản phẩm có tên trùng khớp");
                product.displayData();
                nameExist = true;
            }
        }
        if (!nameExist)
            System.out.println("Không có sản phẩm trùng tên");
        return nameInput;
    }
}
