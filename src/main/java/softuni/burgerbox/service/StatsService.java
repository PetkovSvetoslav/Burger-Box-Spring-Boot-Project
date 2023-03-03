package softuni.burgerbox.service;


import softuni.burgerbox.model.view.StatsView;

public interface StatsService {
  void onRequest();
  StatsView getStats();
  void addOrder();
}
