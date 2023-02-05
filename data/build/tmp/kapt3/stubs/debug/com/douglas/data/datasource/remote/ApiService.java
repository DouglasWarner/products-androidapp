package com.douglas.data.datasource.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/douglas/data/datasource/remote/ApiService;", "", "getRates", "", "Lcom/douglas/data/datasource/remote/model/RateDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactions", "Lcom/douglas/data/datasource/remote/model/TransactionDto;", "data_debug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "transactions")
    public abstract java.lang.Object getTransactions(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.douglas.data.datasource.remote.model.TransactionDto>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "rates")
    public abstract java.lang.Object getRates(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.douglas.data.datasource.remote.model.RateDto>> continuation);
}