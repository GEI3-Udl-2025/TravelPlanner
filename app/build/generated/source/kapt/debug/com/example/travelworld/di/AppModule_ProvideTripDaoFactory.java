package com.example.travelworld.di;

import com.example.travelworld.data.local.TripDatabase;
import com.example.travelworld.data.local.dao.TripDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AppModule_ProvideTripDaoFactory implements Factory<TripDao> {
  private final Provider<TripDatabase> dbProvider;

  public AppModule_ProvideTripDaoFactory(Provider<TripDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TripDao get() {
    return provideTripDao(dbProvider.get());
  }

  public static AppModule_ProvideTripDaoFactory create(Provider<TripDatabase> dbProvider) {
    return new AppModule_ProvideTripDaoFactory(dbProvider);
  }

  public static TripDao provideTripDao(TripDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTripDao(db));
  }
}
