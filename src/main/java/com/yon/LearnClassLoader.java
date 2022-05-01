package com.yon;

public class LearnClassLoader extends ClassLoader {
    public LearnClassLoader(){
        super(LearnClassLoader.class.getClassLoader());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
