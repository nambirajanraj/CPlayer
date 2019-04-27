import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';
import { async } from 'q';


describe('CPlayerUI App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });


  
  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CPlayerUI');
  });


  it('should be directed to home  on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('');
  });

   
  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.driver.sleep(1000);
    browser.element(by.id('firstName')).sendKeys('Nambi');
    browser.element(by.id('lastName')).sendKeys('Rajan');
    browser.element(by.id('userId')).sendKeys('Nambi1234');
    browser.element(by.id('password')).sendKeys('pass1234');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login user and navigate to search players', () => {
    browser.element(by.id('userId')).sendKeys('Nambi1234');
    browser.element(by.id('password')).sendKeys('pass1234');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/players/search');
  });

 

});
