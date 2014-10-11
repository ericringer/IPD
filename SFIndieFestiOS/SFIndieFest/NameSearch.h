//
//  NameSearch.h
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "NameSearchResult.h"
@interface NameSearch : NSObject
-(void)performSearch:(NSString *)search ViewController:(UIViewController *)controller ResultBlock:(void (^) (NameSearchResult * result)) block;
@end
