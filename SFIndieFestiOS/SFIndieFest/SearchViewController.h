//
//  SearchViewController.h
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "NameSearch.h"
#import "NameSearchResult.h"

@interface SearchViewController : UIViewController<UITableViewDataSource,UITableViewDelegate>

-(IBAction)menuButton:(id)sender;
@property (strong, nonatomic) IBOutlet UITextField *txtSearch;
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end
