package com.douglas.data.di;

import com.douglas.data.datasource.remote.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DataModule_ProvideAPIClientFactory implements Factory<ApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public DataModule_ProvideAPIClientFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ApiService get() {
    return provideAPIClient(retrofitProvider.get());
  }

  public static DataModule_ProvideAPIClientFactory create(Provider<Retrofit> retrofitProvider) {
    return new DataModule_ProvideAPIClientFactory(retrofitProvider);
  }

  public static ApiService provideAPIClient(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideAPIClient(retrofit));
  }
}
