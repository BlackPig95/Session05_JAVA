package ra.model;

import java.util.Scanner;

public class Product
{
    private String productId, productName, description;
    private float importPrice, exportPrice, profit;
    private int quantity;
    private boolean status;

    public Product(String productId, String productName, String description, float importPrice, float exportPrice, float profit, int quantity, boolean status)
    {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.status = status;
    }

    public Product()
    {
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public float getImportPrice()
    {
        return importPrice;
    }

    public void setImportPrice(float importPrice)
    {
        this.importPrice = importPrice;
    }

    public float getExportPrice()
    {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice)
    {
        this.exportPrice = exportPrice;
    }

    public float getProfit()
    {
        return profit;
    }

    public void setProfit(float profit)
    {
        this.profit = profit;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct)
    {
        while (true)//Product ID
        {
            System.out.println("Nhập mã sản phẩm bắt đầu bằng P và có 4 ký tự");
            this.productId = scanner.nextLine().strip();
            if (!productId.startsWith("P"))
                System.out.println("Mã sản phẩm phải bắt đầu bằng chữ P");
            else if (productId.length() != 4)
                System.out.println("Mã sản phẩm phải có 4 ký tự");
            else
            {
                boolean isExist = false;
                for (Product product : arrProduct)
                {
                    if (product == null)
                        break;
                    if (product.productId.equals(this.productId))
                    {
                        System.out.println("Mã sản phẩm này dã tồn tại");
                        isExist = true;
                    }
                }
                if (isExist)
                    break;
            }
        }
        while (true)//Product name
        {
            System.out.println("Nhập tên sản phẩm, độ dài từ 5-50 ký tự");
            this.productName = scanner.nextLine();
            if (productName.length() < 5 || productName.length() > 50)
                System.out.println("Tên sản phẩm phải có dộ dài từ 5-50 ký tự");
            else break;
        }
        while (true)//Import price
        {
            System.out.println("Giá nhập của sản phẩm này là (nhập số lớn hơn 0): ");
            this.importPrice = Float.parseFloat(scanner.nextLine());
            if (importPrice <= 0)
                System.out.println("Giá nhập phải lớn hơn 0");
            else break;
        }
        while (true)//Export price
        {
            System.out.println("Giá bán ra của sản phẩm này là (lớn hơn giá nhập ít nhất 20%): ");
            this.exportPrice = Float.parseFloat(scanner.nextLine());
            if ((exportPrice - importPrice) / importPrice < 0.2)
                System.out.println("Giá bán ra phải lớn hơn ít nhất 20% so với giá nhập");
            else break;
        }
        while (true)//Quantity
        {
            System.out.println("Nhập số lượng của sản phẩm này");
            this.quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0)
                System.out.println("Số lượng hàng nhập phải lớn hơn 0");
            else break;
        }
        System.out.println("Nhập mô tả của sản phẩm");//Description
        this.description = scanner.nextLine();
        while (true)//Status
        {
            System.out.println("Nhập trạng thái của sản phẩm (chỉ được viết true hoặc false)");
            String input = scanner.nextLine();
            if (input.equals("true") || input.equals("false"))
            {
                this.status = Boolean.parseBoolean(input);
                break;
            } else System.out.println("Vui lòng nhập đúng chữ true hoặc false");
        }
    }

    public void displayData()
    {
        System.out.println("Mã sản phẩm là: " + productId);
        System.out.println("Tên sản phẩm là: " + productName);
        System.out.println("Giá nhập vào của sản phẩm là: " + importPrice);
        System.out.println("Giá bán ra của sản phẩm là: " + exportPrice);
        System.out.println("Số lượng của sản phẩm là: " + quantity);
        System.out.println("Thông tin mô tả sản phẩm: " + description);
        System.out.println("Trạng thái của sản phẩm: " + (status ? "Đang bán" : "Không bán"));
    }

    public void callProfit()
    {
        System.out.println("Lợi nhuận khi bán sản phẩm này là: " + (exportPrice - importPrice));
    }
}
