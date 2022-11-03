INSERT INTO petshop.categoria (codigo, nome) VALUES(1, 'Ração'),(2, 'Brinquedo'),(3, 'Biscoitos e Petiscos');

INSERT INTO petshop.produto
(codigo, descricao, imagemurl, nome, quantidade, valor, categoria_id)
VALUES(1, 'Ração Premium 100% completa e balanceada para cães filhotes', 'https://www.petlove.com.br/images/products/259806/large/Ra%C3%A7%C3%A3o_Pedigree_Carne_Frango_e_Cereais_C%C3%A3es_Filhotes_18_kg_2513333.png?1659451710', 'Ração Seca Pedigree para Cães Filhotes Raças Médias e Grandes 18kg', 50, 21032.00, 1)
,
(2, 'Proteínas de alta qualidade: favorece o ótimo aproveitamento dos nutrientes para a manutenção da musculatura e condição corporal ideal;', 'https://www.petlove.com.br/images/products/250507/large/2492303_FRENTE.jpg?1635780028', 'Ração Seca Nutrilus Pro Frango & Carne para Cães Adultos 20kg', 50, 15192.00, 1)
,
(3, '23% de proteína e 10% de gordura;', 'https://www.petlove.com.br/images/products/248826/large/_0182_Ra%C3%A7%C3%A3o_Optimum_para_C%C3%A3es_Adultos__de_Ra%C3%A7as_Pequenas_e_Minis_1__anos_Frango_e_Arroz_20_kg_2034862_175.jpg?1633709773', 'Ração Seca Optimum Frango e Arroz para Cães Adultos Raças Pequenas e Minis', 50, 23399.00, 1)
,
(4, '- Sem corantes artificiais;', 'https://www.petlove.com.br/images/products/249350/large/Biscoito_Suprema_para_C%C3%A3es_Adultos_Ra%C3%A7as_M%C3%A9dias_e_Grandes_2724992_%281%29.jpg?1634831611', 'Biscoito Suprema para Cães Adultos Raças Médias e Grandes', 50, 1799.00, 3)
,
(5, '- Resistente;', 'https://www.petlove.com.br/images/products/186132/large/3100921.jpg?1627604002', 'Brinquedo Furacão Pet Dental Bone Algodão com Nó', 50, 981.00, 2);


