# Projeto final da disciplina de Projeto de Software
## UFCG

Este repositório se refere ao backend.
O frontend está neste outro [repositório](https://github.com/rafaelgdp/psoft-projeto-front.git).

## Aluno:

* Rafael Guerra de Pontes

## Estrutura das pastas

O código principal da aplicação está na pasta src. Dentro, existem pacotes como: exceptions e rest. O primeiro contém subpacotes e classes referentes às diversas exceções que o sistema lança. O segundo contém diversos subpacotes da arquitetura REST. São eles:

* controllers: contém os controllers da aplicação com o tratamento resultante das principais funcionalidades oferecidas pela API. Algumas operações são: login, cadastro de usuário, disciplinas, adição de comentários, likes, remoção de comentários, likes.

* dao: classes com operações de persistência das entidades do modelo no bd.

* filters: filtros dos tokens de login.

* model: classes com as principais entidades do sistema (user, comment, course)

* service: serviços que interfaceiam com os DAOs que podem ser acessados pelos controllers.
