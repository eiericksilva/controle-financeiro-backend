package com.eiericksilva.controle_financeiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.eiericksilva.controle_financeiro.entities.Account;
import com.eiericksilva.controle_financeiro.entities.Category;
import com.eiericksilva.controle_financeiro.entities.Subcategory;
import com.eiericksilva.controle_financeiro.entities.Tag;
import com.eiericksilva.controle_financeiro.entities.Transaction;
import com.eiericksilva.controle_financeiro.entities.User;
import com.eiericksilva.controle_financeiro.enums.CategoryType;
import com.eiericksilva.controle_financeiro.enums.TransactionType;
import com.eiericksilva.controle_financeiro.repositories.AccountRepository;
import com.eiericksilva.controle_financeiro.repositories.CategoryRepository;
import com.eiericksilva.controle_financeiro.repositories.SubcategoryRepository;
import com.eiericksilva.controle_financeiro.repositories.TagRepository;
import com.eiericksilva.controle_financeiro.repositories.TransactionRepository;
import com.eiericksilva.controle_financeiro.repositories.UserRepository;

import java.math.BigDecimal;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {

        /* USER */
        User user1 = new User(null, "eiericksilva", "Erick", "Oliveira da Silva");
        User user2 = new User(null, "bê", "Evelyn", "Fernanda");

        userRepository.save(user1);
        userRepository.save(user2);

        /* ACCOUNT */
        Account account1 = new Account(null, "Carteira", BigDecimal.valueOf(200), user1);
        Account account2 = new Account(null, "Nubank", BigDecimal.valueOf(2550), user1);

        accountRepository.save(account1);
        accountRepository.save(account2);

        /* CATEGORY */
        /* Expense */
        Category almoco = new Category(null, "Almoço", CategoryType.EXPENSE);
        Category automovel = new Category(null, "Automóvel", CategoryType.EXPENSE);

        /* Income */
        Category aluguel = new Category(null, "Aluguel", CategoryType.INCOME);
        Category investimentos = new Category(null, "Investimentos", CategoryType.INCOME);

        categoryRepository.save(almoco);
        categoryRepository.save(automovel);
        categoryRepository.save(aluguel);
        categoryRepository.save(investimentos);

        /* SUBCATEGORY */
        /* Expense */
        Subcategory combustivel = new Subcategory(null, "Combustível", automovel);
        /* Income */
        Subcategory bonificacao = new Subcategory(null, "Bonificação", investimentos);

        subcategoryRepository.save(combustivel);
        subcategoryRepository.save(bonificacao);

        /* TAG */
        Tag tagA = new Tag(null, "TagA");
        Tag tagB = new Tag(null, "TagB");
        Tag tagC = new Tag(null, "TagC");

        tagRepository.save(tagA);
        tagRepository.save(tagB);
        tagRepository.save(tagC);

        /* TRANSACTION */
        Transaction t1 = new Transaction();
        t1.setAmount(BigDecimal.valueOf(30));
        t1.setCategory(automovel);
        t1.setSubcategory(combustivel);
        t1.setDescription("Combustível para ir em Ipatinga");
        t1.setIsConfirmed(true);
        t1.setTransactionType(TransactionType.EXPENSE);
        transactionRepository.save(t1);

        t1.addTag(tagA);
        t1.addTag(tagC);
        transactionRepository.save(t1);

    }

}
