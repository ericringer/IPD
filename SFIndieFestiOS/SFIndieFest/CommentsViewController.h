//
//  CommentsViewController.h
//  SFIndieFest
//
//  Created by eric ringer on 9/18/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CommentsViewController : UIViewController<UITableViewDataSource,UITableViewDataSource,UIAlertViewDelegate>
-(IBAction)menuButton:(id)sender;
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end
