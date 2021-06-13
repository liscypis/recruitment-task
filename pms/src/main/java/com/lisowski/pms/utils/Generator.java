package com.lisowski.pms.utils;

import com.lisowski.pms.entity.*;
import com.lisowski.pms.repository.CategoryRepository;
import com.lisowski.pms.repository.MessageRepository;
import com.lisowski.pms.repository.ProductRepository;
import com.lisowski.pms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Generator implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final MessageRepository messageRepository;
    private final PasswordEncoder passwordEncoder;
    Random random = new Random();


    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        categoryRepository.deleteAll();
        productRepository.deleteAll();
        messageRepository.deleteAll();

        String[] amdCPU = {"Athlon Gold 3150G", "Athlon Gold 3150GE", "Athlon Gold PRO 3150G", "Athlon Gold PRO 3150GE", "Athlon Silver 3050GE", "Athlon Silver PRO 3125GE", "EPYC 7F32", "EPYC 7F52", "EPYC 7F72", "Ryzen 3 3100", "Ryzen 3 3250U", "Ryzen 3 3300X", "Ryzen 3 4300G", "Ryzen 3 4300GE", "Ryzen 3 4300U", "Ryzen 3 PRO 4350G", "Ryzen 3 PRO 4350GE", "Ryzen 5 4500U", "Ryzen 5 4600G", "Ryzen 5 4600GE", "Ryzen 5 4600H", "Ryzen 5 4600HS", "Ryzen 5 4600U", "Ryzen 5 5600X", "Ryzen 5 PRO 4650G", "Ryzen 5 PRO 4650GE", "Ryzen 7 3800XT", "Ryzen 7 4700G", "Ryzen 7 4700GE", "Ryzen 7 4700U", "Ryzen 7 4800H", "Ryzen 7 4800HS", "Ryzen 7 4800U", "Ryzen 7 5800X", "Ryzen 7 PRO 4750G", "Ryzen 7 PRO 4750GE", "Ryzen 9 3900XT", "Ryzen 9 4900H", "Ryzen 9 4900HS", "Ryzen 9 5900X", "Ryzen 9 5950X", "Ryzen Threadripper 3990X", "Ryzen Threadripper PRO 3945WX", "Ryzen Threadripper PRO 3955WX", "Ryzen Threadripper PRO 3975WX", "Ryzen Threadripper PRO 3995WX"};
        String[] amdCPUDescription = {"Socket AM4	12 nm	4MB	65 W", "Socket AM4	12 nm	4MB	35 W", "Socket AM4	12 nm	4MB	65 W", "Socket AM4	12 nm	4MB	35 W", "Socket AM4	12 nm	4MB	35 W", "Socket AM4	12 nm	4MB	35 W", "Socket SP3	7 nm	128MB	180 W", "Socket SP3	7 nm	256MB	155 W", "Socket SP3	7 nm	192MB	240 W", "Socket AM4	7 nm	16MB	65 W", "Socket FP5	12 nm	4MB	15 W", "Socket AM4	7 nm	16MB	65 W", "Socket AM4	7 nm	4MB	65 W", "Socket AM4	7 nm	4MB	35 W", "Socket FP6	7 nm	4MB	25 W", "Socket AM4	7 nm	4MB	65 W", "Socket AM4	7 nm	4MB	35 W", "Socket FP6	7 nm	11MB	25 W", "Socket AM4	7 nm	8MB	65 W", "Socket AM4	7 nm	8MB	35 W", "Socket FP6	7 nm	11MB	54 W", "Socket FP6	7 nm	11MB	35 W", "Socket FP6	7 nm	11MB	25 W", "Socket AM4	7 nm	32MB	65 W", "Socket AM4	7 nm	8MB	65 W", "Socket AM4	7 nm	8MB	35 W", "Socket AM4	7 nm	32MB	105 W", "Socket AM4	7 nm	8MB	65 W", "Socket AM4	7 nm	8MB	35 W", "Socket FP6	7 nm	12MB	25 W", "Socket FP6	7 nm	12MB	54 W", "Socket FP6	7 nm	12MB	35 W", "Socket FP6	7 nm	8MB	25 W", "Socket AM4	7 nm	32MB	105 W", "Socket AM4	7 nm	8MB	65 W", "Socket AM4	7 nm	8MB	35 W", "Socket AM4	7 nm	64MB	105 W", "Socket FP6	7 nm	12MB	54 W", "Socket FP6	7 nm	12MB	35 W", "Socket AM4	7 nm	64MB	105 W", "Socket AM4	7 nm	64MB	105 W", "Socket TRX4	7 nm	128MB	280 W", "Socket WRX8	7 nm	64MB	280 W", "Socket WRX8	7 nm	64MB	280 W", "Socket WRX8	7 nm	128MB	280 W", "Socket WRX8	7 nm	256MB	280 W"};
        String[] amdGPU = {"Radeon Instinct MI100", "Radeon Pro 5300", "Radeon Pro 5500 XT", "Radeon Pro 5700", "Radeon Pro 5700 XT", "Radeon Pro V520", "Radeon Pro VII", "Radeon Pro W5500", "Radeon Pro W6900X", "Radeon RX 5300", "Radeon RX 5600 OEM", "Radeon RX 5600 XT", "Radeon RX 590 GME", "Radeon RX 6800", "Radeon RX 6800 XT", "Radeon RX 6900 XT", "Radeon RX 6900 XTX"};
        String[] amdGPUDescription = {"32 GB HBM2 4096 bit", "4 GB GDDR6 128 bit", "8 GB GDDR6 128 bit", "8 GB GDDR6 256 bit", "16 GB GDDR6 256 bit", "8 GB HBM2 2048 bit", "16 GB HBM2 4096 bit", "8 GB GDDR6 128 bit", "32 GB GDDR6 256 bit", "3 GB GDDR6 96 bit", "6 GB GDDR6 192 bit", "6 GB GDDR6 192 bit", "8 GB GDDR5 256 bit", "16 GB GDDR6 256 bit", "16 GB GDDR6 256 bit", "16 GB GDDR6 256 bit", "16 GB GDDR6 256 bit"};
        String[] nvidiaGPU = {"GeForce GT 1030 DDR4", "GeForce GT 1030 GK107", "GeForce GTX 1050 3 GB", "GeForce GTX 1060 6 GB GDDR5X", "GeForce GTX 1060 6 GB GP104", "GeForce GTX 1060 6 GB Rev. 2", "GeForce GTX 1060 8 GB GDDR5X", "GeForce GTX 1070 GDDR5X", "GeForce GTX 1080 Ti 10 GB", "GeForce RTX 2070", "GeForce RTX 2080", "GeForce RTX 2080 Ti", "P102-100", "P102-101", "P104-101", "Quadro GV100", "Quadro P620", "Quadro RTX 4000", "Quadro RTX 5000", "Quadro RTX 6000", "Quadro RTX 6000 Passive", "Quadro RTX 8000", "Quadro RTX 8000 Passive", "TITAN RTX", "TITAN V CEO Edition", "Tesla T4", "Tesla V100 DGXS 16 GB", "Tesla V100 DGXS 32 GB", "Tesla V100 FHHL", "Tesla V100 PCIe 32 GB", "Tesla V100 SXM2 32 GB", "Tesla V100 SXM3 32 GB"};
        String[] nvidiaGPUDescription = {"2 GB DDR4 64 bit", "2 GB GDDR5 128 bit", "3 GB GDDR5 96 bit", "6 GB GDDR5X 192 bit", "6 GB GDDR5 192 bit", "6 GB GDDR5 192 bit", "8 GB GDDR5X 256 bit", "8 GB GDDR5X 256 bit", "10 GB GDDR5X 320 bit", "8 GB GDDR6 256 bit", "8 GB GDDR6 256 bit", "11 GB GDDR6 352 bit", "5 GB GDDR5X 320 bit", "10 GB GDDR5 320 bit", "4 GB GDDR5 256 bit", "32 GB HBM2 4096 bit", "2 GB GDDR5 128 bit", "8 GB GDDR6 256 bit", "16 GB GDDR6 256 bit", "24 GB GDDR6 384 bit", "24 GB GDDR6 384 bit", "48 GB GDDR6 384 bit", "48 GB GDDR6 384 bit", "24 GB GDDR6 384 bit", "32 GB HBM2 4096 bit", "16 GB GDDR6 256 bit", "16 GB HBM2 4096 bit", "32 GB HBM2 4096 bit", "16 GB HBM2 4096 bit", "32 GB HBM2 4096 bit", "32 GB HBM2 4096 bit", "32 GB HBM2 4096 bit"};
        String[] intelCPU = {"Core i3-10105", "Core i3-10105F", "Core i3-10105T", "Core i3-10305", "Core i3-10305T", "Core i3-10325", "Core i5-11260H", "Core i5-11400", "Core i5-11400F", "Core i5-11400H", "Core i5-11400T", "Core i5-11500", "Core i5-11500H", "Core i5-11500T", "Core i5-1155G7", "Core i5-11600", "Core i5-11600K", "Core i5-11600KF", "Core i5-11600T", "Core i7-11700", "Core i7-11700F", "Core i7-11700K", "Core i7-11700KF", "Core i7-11700T", "Core i7-11800H", "Core i7-11850H", "Core i7-1195G7", "Core i9-11900", "Core i9-11900F", "Core i9-11900H", "Core i9-11900K", "Core i9-11900KB", "Core i9-11900KF", "Core i9-11900T", "Core i9-11950H", "Core i9-11980HK", "Pentium Gold G6405", "Pentium Gold G6405T", "Pentium Gold G6505", "Pentium Gold G6505T", "Pentium Gold G6605", "Xeon Gold 5315Y", "Xeon Gold 5317", "Xeon Gold 5318H", "Xeon Gold 5318N", "Xeon Gold 5318S", "Xeon Gold 5318Y", "Xeon Gold 5320", "Xeon Gold 5320H", "Xeon Gold 5320T", "Xeon Gold 6312U", "Xeon Gold 6314U", "Xeon Gold 6326", "Xeon Gold 6328H", "Xeon Gold 6328HL", "Xeon Gold 6330", "Xeon Gold 6330H", "Xeon Gold 6334", "Xeon Gold 6336Y", "Xeon Gold 6338N", "Xeon Gold 6338T", "Xeon Gold 6342", "Xeon Gold 6346", "Xeon Gold 6348", "Xeon Gold 6348H", "Xeon Gold 6354", "Xeon Platinum 8351N", "Xeon Platinum 8352M", "Xeon Platinum 8352S", "Xeon Platinum 8352V", "Xeon Platinum 8353H", "Xeon Platinum 8354H", "Xeon Platinum 8356H", "Xeon Platinum 8358", "Xeon Platinum 8358P", "Xeon Platinum 8360H", "Xeon Platinum 8360HL", "Xeon Platinum 8360Y", "Xeon Platinum 8362", "Xeon Platinum 8368", "Xeon Platinum 8368Q", "Xeon Platinum 8376H", "Xeon Platinum 8376HL", "Xeon Platinum 8380", "Xeon Platinum 8380H", "Xeon Platinum 8380HL", "Xeon Silver 4309Y", "Xeon Silver 4310", "Xeon Silver 4310T", "Xeon Silver 4314", "Xeon Silver 4316", "Xeon W-11855M", "Xeon W-11955M"};
        String[] intelCPUDescription = {"14 nm	6MB	65 W", "14 nm	6MB	65 W", "14 nm	6MB	35 W", "14 nm	8MB	65 W", "14 nm	8MB	35 W", "14 nm	8MB	65 W", "10 nm	12MB	35 W", "14 nm	12MB	65 W", "14 nm	12MB	65 W", "10 nm	12MB	35 W", "14 nm	12MB	35 W", "14 nm	12MB	65 W", "10 nm	12MB	35 W", "14 nm	12MB	35 W", "10 nm	8MB	28 W", "14 nm	12MB	65 W", "14 nm	12MB	125 W", "14 nm	12MB	125 W", "14 nm	12MB	35 W", "14 nm	16MB	65 W", "14 nm	16MB	65 W", "14 nm	16MB	125 W", "14 nm	16MB	125 W", "14 nm	16MB	35 W", "10 nm	24MB	35 W", "10 nm	24MB	35 W", "10 nm	12MB	28 W", "14 nm	16MB	65 W", "14 nm	16MB	65 W", "10 nm	24MB	35 W", "14 nm	16MB	125 W", "10 nm	24MB	65 W", "14 nm	16MB	125 W", "14 nm	16MB	35 W", "10 nm	24MB	35 W", "10 nm	24MB	65 W", "14 nm	4MB	65 W", "14 nm	4MB	35 W", "14 nm	4MB	65 W", "14 nm	4MB	35 W", "14 nm	4MB	65 W", "10 nm	12MB	140 W", "10 nm	18MB	150 W", "14 nm	24.75MB	150 W", "10 nm	36MB	150 W", "10 nm	36MB	165 W", "10 nm	36MB	165 W", "10 nm	39MB	185 W", "14 nm	27.5MB	150 W", "10 nm	30MB	150 W", "10 nm	36MB	185 W", "10 nm	48MB	205 W", "10 nm	24MB	185 W", "14 nm	22MB	165 W", "14 nm	22MB	165 W", "10 nm	42MB	205 W", "14 nm	33MB	150 W", "10 nm	18MB	165 W", "10 nm	36MB	185 W", "10 nm	48MB	185 W", "10 nm	36MB	165 W", "10 nm	36MB	230 W", "10 nm	36MB	205 W", "10 nm	42MB	235 W", "14 nm	33MB	165 W", "10 nm	39MB	205 W", "10 nm	54MB	225 W", "10 nm	48MB	185 W", "10 nm	48MB	205 W", "10 nm	54MB	195 W", "14 nm	24.75MB	150 W", "14 nm	24.75MB	205 W", "14 nm	35.75MB	190 W", "10 nm	48MB	250 W", "10 nm	48MB	240 W", "14 nm	33MB	225 W", "14 nm	33MB	225 W", "10 nm	54MB	250 W", "10 nm	48MB	265 W", "10 nm	57MB	270 W", "10 nm	57MB	270 W", "14 nm	38.5MB	205 W", "14 nm	38.5MB	205 W", "10 nm	60MB	270 W", "14 nm	38.5MB	250 W", "14 nm	38.5MB	250 W", "10 nm	12MB	105 W", "10 nm	18MB	120 W", "10 nm	15MB	105 W", "10 nm	24MB	135 W", "10 nm	30MB	150 W", "10 nm	18MB	35 W", "10 nm	24MB	35 W"};

        User user = generateUser("admin", "test", ERole.ROLE_ADMIN, "jacek@o2.pl", "111111111");
        User user2 = generateUser("test1", "test1", ERole.ROLE_USER, "michal@o2.pl", "211111112");


        generateMessage(user2, "Dostepnosc 2080", "Kiedy bedzie dostepna???");
        generateMessage(user2, "Dostepnosc GPU", "xxxxxxxxxxxxxxx xxxxxxxxxxx xxxxxxxx ddddddddddd ddddddddddd dddddddd ddddddd");

        Category amdCPUCategory = generateCategory("AMD CPU");
        Category amdGPUCategory = generateCategory("AMD GPU");
        Category nvidiaGPUCategory = generateCategory("NVIDIA");
        Category intelCPUCategory = generateCategory("INTEL");

        generateProducts(intelCPU, intelCPUDescription, intelCPUCategory);
        generateProducts(amdCPU, amdCPUDescription, amdCPUCategory);
        generateProducts(amdGPU, amdGPUDescription, amdGPUCategory);
        generateProducts(nvidiaGPU, nvidiaGPUDescription, nvidiaGPUCategory);
    }

    private void generateProducts(@NotNull String[] products, String[] productDescription, Category category) {
        List<Product> productList = new ArrayList<>(products.length);
        for (int i = 0; i < products.length; i++) {
            Product product = new Product();
            product.setProductName(products[i]);
            product.setAvailable(random.nextBoolean());
            product.setDescription(productDescription[i]);
            product.setNetPrice(random.nextInt(10000) + 301);
            product.setVat(random.nextInt(100));
            product.updateGrossPrice();
            product.setCategory(category);
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    private User generateUser(String username, String password, ERole role, String email, String phoneNum) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setEmail(email);
        user.setPhoneNumber(phoneNum);
        return userRepository.save(user);
    }

    @NotNull
    private Category generateCategory(String s) {
        Category category = new Category();
        category.setName(s);
        category = categoryRepository.save(category);
        return category;
    }

    private void generateMessage(User user, String subject, String msg) {
        Message message = new Message();
        message.setSubject(subject);
        message.setMessage(msg);
        message.setUser(user);
        messageRepository.save(message);
    }
}
