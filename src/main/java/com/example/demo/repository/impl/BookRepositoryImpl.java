package com.example.demo.repository.impl;

//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class BookRepositoryImpl implements BookRepository {
//
//    private HashMap<Long, Book> books;
//    private Long counter;
//    @PostConstruct
//    public void init(){
//        this.counter = 0L;
//        this.books = new HashMap<>();
//        Long id = this.generateKey();
//        this.books.put(id,new Book(id,"Life Of Pi",15,null,"https://hips.hearstapps.com/vader-prod.s3.amazonaws.com/1572292455-51rxEvLljUL.jpg?crop=1xw:0.972xh;center,top&resize=320%3A%2A",30));
//        id = this.generateKey();
//        this.books.put(id,new Book(id,"Watchmen",7,null,"https://hips.hearstapps.com/vader-prod.s3.amazonaws.com/1572292885-41ZJD5Dw5KL.jpg?crop=1.00xw:0.962xh;0,0.0200xh&resize=320%3A%2A",40));
//        this.generateKey();
//        this.books.put(id,new Book(id,"The Night Fire",10,null,"https://hips.hearstapps.com/vader-prod.s3.amazonaws.com/1572293115-41zqZwXZ-WL.jpg?crop=1.00xw:0.984xh;0,0.00600xh&resize=320%3A%2A",60));
//        id = this.generateKey();
//        this.books.put(id,new Book(id,"The Water Dancer",20,null,"https://hips.hearstapps.com/vader-prod.s3.amazonaws.com/1572293716-51ItgiZw5dL.jpg?crop=1xw:0.987xh;center,top&resize=320%3A%2A",10));
//        id = this.generateKey();
//        this.books.put(id,new Book(id,"The Help",3,null,"https://hips.hearstapps.com/vader-prod.s3.amazonaws.com/1572293965-51D9LgMgL.jpg?crop=1xw:0.999xh;center,top&resize=320%3A%2A",100));
//    }
//
//
//    @Override
//    public List<Book> findAll() {
//        return new ArrayList<>(this.books.values());
//    }
//
//    @Override
//    public List<Book> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Book> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<Book> findAllById(Iterable<Long> iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public <S extends Book> List<S> saveAll(Iterable<S> iterable) {
//        return null;
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends Book> S saveAndFlush(S s) {
//        return null;
//    }
//
//    @Override
//    public void deleteInBatch(Iterable<Book> iterable) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public Book getOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public <S extends Book> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends Book> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Book> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends Book> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public Optional<Book> findById(Long id) {
//        return Optional.ofNullable(this.books.get(id));
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public Book save(Book book) {
//        if(book.getId() == null) {
//            book.setId(this.generateKey());
//        }
//        this.books.put(book.getId(), book);
//        return book;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        this.books.remove(id);
//    }
//
//    @Override
//    public void delete(Book book) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Book> iterable) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    private Long generateKey() {
//        return ++counter;
//    }
//
//    @Override
//    public List<Book> findAllByCategoryrId(Long id) {
//        return this.books.values()
//                .stream()
//                .filter(item -> item.getCategory().getId().equals(id))
//                .collect(Collectors.toList());
//
//    }
//}
